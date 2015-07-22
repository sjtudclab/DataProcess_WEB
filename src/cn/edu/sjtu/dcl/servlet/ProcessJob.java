package cn.edu.sjtu.dcl.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.io.LongWritable;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.dao.impl.DB_JobRelation;
import cn.edu.sjtu.dcl.mapreduce.creator.impl.MapReduceCreator;
import cn.edu.sjtu.dcl.mapreduce.executor.impl.MapReduceExecutor;
import cn.edu.sjtu.dcl.mapreduce.generator.impl.MRCodeGenerator;
import cn.edu.sjtu.dcl.oozie.creator.impl.OozieAppCreator;
import cn.edu.sjtu.dcl.oozie.executor.impl.OozieExecutor;
import cn.edu.sjtu.dcl.oozie.parser.impl.OozieParser;
import cn.edu.sjtu.dcl.util.ProjectConstants;
import cn.edu.sjtu.dcl.util.PropertiesHandler;
import cn.edu.sjtu.dcl.validation.impl.ValidatorFacade;

/**
 * Servlet implementation class ProcessJob
 */
public class ProcessJob extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessJob() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private String driverName = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String userPwd = "123456";
	private String dbName = "hadoop";
	private String url = "jdbc:mysql://172.30.0.148/" + dbName + "?user="
			+ userName + "&password=" + userPwd;
	private Connection conn = null;
	public Statement sm = null;
	public PreparedStatement ps = null;

	public void ConnectDB() {
		try {
			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(url);
			sm = conn.createStatement();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void ConnectPreparedDB(String sql) {
		try {
			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(url);
			ps = conn.prepareStatement(sql);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void CloseDB() {
		try {
			if (sm != null) {
				sm.close();
			}
			if (ps != null) {
				ps.close();
			}
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---------------------here in ProcessJob doPost");
		User users = (User) request.getSession().getAttribute(
				ProjectConstants.USER_SESSION_KEY);
		System.out.println(users.getId());

		String schemaPath = "resource/JCDL.xsd";
		PrintWriter out = response.getWriter();
		BufferedReader br = request.getReader();

		StringBuffer sb = new StringBuffer();
		String value = null;

		try {
			while ((value = br.readLine()) != null) {
				sb.append(value);
				sb.append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();

			if (sb.length() > 1) {
				sb.delete(0, sb.length() - 1);
			}
			
			File file = new File("/root/input.txt");
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
				int line = 1;
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
					// 显示行号
					line++;
					sb.append(tempString);
					sb.append("\r\n");
				}
				reader.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
		}

		br.close();

		int style = 0;

		String xml = new String(sb.toString().getBytes("utf-8"));
		xml = xml.trim();
		if (xml.contains("averagescore") && xml.contains("scorecount")) {
			style = 3;
		} else if (xml.contains("averagescore")) {
			style = 1;
		} else if (xml.contains("scorecount")) {
			style = 2;
		}

		System.out.println("style:" + style);
		if (1 == style) {
			System.out.println("iffffffffffffffffffffffffffffffff");
			AverageScore();
		} else if (2 == style) {
			ScoreCount();
		} else if (3 == style) {
			AverageScore();
			ScoreCount();
		}

		System.out.println("xmlllllllllllllll:");
		System.out.println(xml);

		String para = xml.substring(xml.indexOf("para") + 4,
				xml.indexOf("/para"));
		System.out.println(para);
		String[] parameter = para.split(",");
		xml = xml.substring(xml.indexOf('<'), xml.length());
		System.out.println(xml);
		String jcdl = System.currentTimeMillis() + ".jcdl.xml";
		String path = request.getSession().getServletContext().getRealPath("/");
		BufferedWriter bw = new BufferedWriter(new FileWriter(path + "/jcdl/"
				+ jcdl));
		bw.write(xml);
		bw.flush();
		bw.close();
		ValidatorFacade validator = new ValidatorFacade(path + File.separator
				+ schemaPath);

		boolean isValid = validator.validate(path + "/jcdl/" + jcdl);
		if (!isValid) {
			out.write("jcdl is not valid");
			return;
		}
		String[] cluster = applyForCluster();
		if (parameter[2].equals("oozie")) {
			try {
				OozieParser op = new OozieParser(jcdl, cluster[0], cluster[1]);
				String appPath = path + "/hpdl/"
						+ jcdl.substring(0, jcdl.indexOf('.'));
				op.parse(path + "/jcdl/" + jcdl, appPath + "workflow.xml");
				OozieAppCreator oc = new OozieAppCreator();
				oc.create(appPath + "workflow.xml", op.getLibs(), appPath
						+ "/app");
				String namenode = cluster[0];

				System.out.println("path:" + path);
				System.out.println("app Path:" + appPath);

				OozieExecutor oz = new OozieExecutor(namenode, path);
				oz.employ(appPath);

				String jobId = oz.execute(appPath);

				if (null == jobId || jobId.isEmpty()) {
					out.write("oozie execution failed with jobId null");
				} else {
					User user = (User) request.getSession().getAttribute(
							ProjectConstants.USER_SESSION_KEY);

					DB_JobRelation db = new DB_JobRelation();
					db.MapJobRelation(-1, jobId, user.getId());

					out.write(jobId + " is submitted");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				out.write("oozie execution failed");
			}
		} else if (parameter[2].equals("mapreduce")) {
			try {
				MRCodeGenerator mcg = new MRCodeGenerator();
				mcg.generate(path + "/jcdl/" + jcdl, "vm/generateMRCode.vm",
						"Driver", cluster[0], cluster[1]);
				String libs[] = new String[mcg.getLibs().length + 1];
				for (int i = 0; i < libs.length - 1; i++) {
					libs[i] = mcg.getLibs()[i];
				}
				libs[libs.length - 1] = path
						+ "WEB-INF/lib/hadoop-core-1.0.3.jar";
				String mapreducePath = path + "/mapreduce/"
						+ jcdl.substring(0, jcdl.indexOf('.'));
				MapReduceCreator mrc = new MapReduceCreator();
				mrc.create(libs, mapreducePath, "Driver");
				MapReduceExecutor mre = new MapReduceExecutor();
				String s = mre.execute(mapreducePath);
				out.print(s);
			} catch (Exception e) {
				out.write("mapreduce execution failed");
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();

	}

	private void AverageScore() {

		System.out.println("ResultSet:" + "begin:--------------");

		HashMap<Long, ArrayList<Long>> scoreMap = new HashMap<Long, ArrayList<Long>>();
		ConnectDB();
		ResultSet rs = null;
		try {
			String sql = "select * from StudentScore";
			rs = sm.executeQuery(sql);

			System.out.println("ResultSet:" + "here:--------------");

			while (rs.next()) {
				long studentId = rs.getLong("studentId");
				long courseId = rs.getLong("courseId");
				long score = rs.getLong("score");

				System.out.println("studentId:" + studentId);
				System.out.println("courseId:" + courseId);
				System.out.println("score:" + score);

				if (scoreMap.containsKey(studentId)) {
					ArrayList<Long> scores = scoreMap.get(studentId);
					scores.add(score);
				} else {
					ArrayList<Long> scores = new ArrayList<Long>();
					scores.add(score);
					scoreMap.put(studentId, scores);
				}
			}

			Set<Long> keys = scoreMap.keySet();
			for (long id : keys) {
				ArrayList<Long> scores = scoreMap.get(id);

				long ave = 0;
				for (long s : scores) {
					ave += s;
				}

				ave /= scores.size();

				String insertSql = "insert into StudentAveScore(studentId, aveScore) values (?, ?)";
				ps = conn.prepareStatement(insertSql);

				ps.setLong(1, id);
				ps.setLong(2, ave);

				ps.executeUpdate();
			}
		} catch (SQLException SqlE) {
			SqlE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			CloseDB();
		}
	}

	private void ScoreCount() {
		ConnectDB();
		ResultSet rs = null;
		try {
			String sql = "select * from StudentAveScore";
			rs = sm.executeQuery(sql);

			int under60 = 0;
			int ll70 = 0;
			int ll80 = 0;
			int ll90 = 0;
			int ll100 = 0;

			while (rs.next()) {
				long studentId = rs.getLong("studentId");
				long score = rs.getLong("aveScore");

				System.out.println("studentId:" + studentId);
				System.out.println("aveScore:" + score);

				if (score < 60) {
					under60++;

				} else if (score >= 60 && score < 70) {
					ll70++;
				} else if (score >= 70 && score < 80) {
					ll80++;
				} else if (score >= 80 && score < 90) {
					ll90++;
				} else if (score >= 90 && score <= 100) {
					ll100++;
				}
			}

			String insertSql = "insert into ScoreCount(score, count) values (?, ?)";
			ps = conn.prepareStatement(insertSql);

			ps.setLong(1, 60);
			ps.setLong(2, under60);

			ps.executeUpdate();

			ps.setLong(1, 70);
			ps.setLong(2, ll70);

			ps.executeUpdate();

			ps.setLong(1, 80);
			ps.setLong(2, ll80);

			ps.executeUpdate();

			ps.setLong(1, 90);
			ps.setLong(2, ll90);

			ps.executeUpdate();

			ps.setLong(1, 100);
			ps.setLong(2, ll100);

			ps.executeUpdate();
		} catch (SQLException SqlE) {
			SqlE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			CloseDB();
		}
	}

	private String[] applyForCluster() {
		String cluster = getServletContext().getRealPath("/")
				+ "WEB-INF/classes/cluster.conf";
		String namenode = PropertiesHandler.getValue(cluster, "namenode");
		String jobtracker = PropertiesHandler.getValue(cluster, "jobtracker");
		String[] s = { namenode, jobtracker };
		return s;
	}

}

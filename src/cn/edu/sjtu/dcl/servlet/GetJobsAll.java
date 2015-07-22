package cn.edu.sjtu.dcl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.impl.DB_Job;
import cn.edu.sjtu.dcl.service.impl.JobServiceImpl;
import cn.edu.sjtu.dcl.service.interfaces.JobService;

public class GetJobsAll extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8812525610379887570L;

	/**
	 * Constructor of the object.
	 */
	public GetJobsAll() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		JobService jobService=new JobServiceImpl();
		List<Job> mrjobs = jobService.getAll();
		sb.append("<jobs>");
		for(Job job: mrjobs)
		{
			sb.append("\r    <job>");
			sb.append("\r        <id>"+job.getId()+"</id>");
			sb.append("\r		 <name>"+job.getName()+"</name>");
			sb.append("\r		 <path>"+job.getPath()+"</path>");
			sb.append("\r		 <mapper>"+job.getMapper()+"</mapper>");
			sb.append("\r		 <reducer>"+job.getReducer()+"</reducer>");
			sb.append("\r		 <inputformat>"+job.getInputFormat()+"</inputformat>");
			sb.append("\r		 <mapinputkey>"+job.getMapInputKey()+"</mapinputkey>");
			sb.append("\r		 <mapinputvalue>"+job.getMapInputValue()+"</mapinputvalue>");
			sb.append("\r		 <mapoutputkey>"+job.getMapOutputKey()+"</mapoutputkey>");
			sb.append("\r		 <mapoutputvalue>"+job.getMapOutputValue()+"</mapoutputvalue>");
			sb.append("\r		 <outputkey>"+job.getOutputKey()+"</outputkey>");
			sb.append("\r		 <outputvalue>"+job.getOutputValue()+"</outputvalue>");
			sb.append("\r		 <parameters>"+job.getParameters()+"</parameters>");
			sb.append("\r		 <iterative>"+(job.isIterative() ? 1 : 0)+"</iterative>");
			sb.append("\r        <isMapType>"+(job.isMapType() ? 1 : 0)+"</isMapType>");
			sb.append("\r        <description>"+job.getDescription()+"</description>");
			sb.append("\r    </job>");
		}
		sb.append("\r</jobs>");
		//System.out.println(sb.toString());
		out.println(sb.toString());
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

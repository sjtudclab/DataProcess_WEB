package cn.edu.sjtu.dcl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.impl.DB_Job;

public class UpdateJob extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4444820908818823704L;

	/**
	 * Constructor of the object.
	 */
	public UpdateJob() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		int idint;
		try {
			idint = Integer.parseInt(id);
			DB_Job dbj = new DB_Job();
			Job mrj = new Job();
			mrj.setId(idint);
			mrj.setName(request.getParameter("name"));
			mrj.setPath(request.getParameter("path"));
			mrj.setMapper(request.getParameter("mapper"));
			mrj.setReducer(request.getParameter("reducer"));
			mrj.setInputFormat(request.getParameter("inputformat"));
			mrj.setMapInputKey(request.getParameter("mapinputkey"));
			mrj.setMapInputValue(request.getParameter("mapinputvalue"));
			mrj.setMapOutputKey(request.getParameter("mapoutputkey"));
			mrj.setMapOutputValue(request.getParameter("mapoutputvalue"));
			mrj.setOutputKey(request.getParameter("outputkey"));
			mrj.setOutputValue(request.getParameter("outputvalue"));
			mrj.setParameters(request.getParameter("parameters"));
			mrj.setIterative(request.getParameter("iterative").equals("0") ? false
					: true);
			mrj.setMapType(request.getParameter("isMapType").equals("0") ? false
					: true);
			mrj.setDescription(request.getParameter("description"));
			dbj.updateJob(mrj);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		out.flush();
		out.close();
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

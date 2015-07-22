package cn.edu.sjtu.dcl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import cn.edu.sjtu.dcl.util.HttpUtil;
import cn.edu.sjtu.dcl.util.ProjectConstants;

public class LocalRedirectServlet extends HttpServlet {
	
	private static final long serialVersionUID = -9220663035877813215L;
	public static final String ACTION_NAME = "action_name";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject json = new JSONObject();
		HttpSession session = req.getSession();
		Object obj = session.getAttribute(ProjectConstants.USER_SESSION_KEY);
		if(obj==null){
			try {
				json.put("message", "you have no auth");
				json.put("status", "failure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String actionName = req.getParameter(ACTION_NAME);
			Map<String, String[]> parameterMap = req.getParameterMap();
			System.out.println(parameterMap.keySet());
			String content = "";
			for (String param : parameterMap.keySet()) {
				if (!param.equals(ACTION_NAME) && !param.equals("authToken")) {
					for (String v : parameterMap.get(param)) {
						if (!content.equals("")) {
							content += "&";
						}
						content += param + "=" + v;
					}
				}
			}
				synchronized (this){
				json = HttpUtil.local_post(actionName.replaceAll("_", "/"),
						content);//TODO
				}
		}

		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		resp.setStatus(200);
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
		out.flush();

		System.out.println(json.toString());
	}

}

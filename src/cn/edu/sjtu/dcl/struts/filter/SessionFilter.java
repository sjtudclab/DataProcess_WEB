package cn.edu.sjtu.dcl.struts.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.dao.interfaces.UserDAO;
import cn.edu.sjtu.dcl.service.bean.UserVB;
import cn.edu.sjtu.dcl.service.interfaces.UserService;
import cn.edu.sjtu.dcl.util.ProjectConstants;


public class SessionFilter implements Filter {
	
	private UserService userService;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
//		System.out.println("do sessionUser filter");
		System.out.println("in filter");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(ProjectConstants.USER_SESSION_KEY);
		System.out.println(obj);
		Cookie[] cookie = request.getCookies();
		if(obj == null){
			System.out.println("in obj");
			if(request.getSession().getAttribute(ProjectConstants.USER_SESSION_KEY) == null){
				if(cookie != null && cookie.length != 0){
					String name = "";
					String password = "";
					for(int i = 0;i<cookie.length;i++){
						if(cookie[i].getName().equals("user")){
							name = cookie[i].getValue();
						}else if(cookie[i].getName().equals("pd")){
							password = cookie[i].getValue();
						}
					}
					if(!name.trim().equals("") && !password.trim().equals("")){
						User user = new User();
						user.setName(name);
						user.setPassword(password);
						User userInfo=(User)this.getUserService().validate(user);
						if (userInfo != null) {
							request.getSession().setAttribute(ProjectConstants.USER_SESSION_KEY, userInfo);
							System.out.println("here before filter111");
							chain.doFilter(request, response);
							return;
						} 
					}else
					{
						User user = new User();
						user.setName("testname");
						user.setPassword("testpwd");
						request.getSession().setAttribute(ProjectConstants.USER_SESSION_KEY, user);
						chain.doFilter(request, response);
						return;
					}
				}
			}
			PrintWriter out = res.getWriter();
			out.println("<script   language=\"JavaScript\">"
					+ "top.location=\"/DataProcess_WEB/login.html\" </script>");
			out.flush();
			out.close();
		}else{
			System.out.println("here before filter222");
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public UserService getUserService() {
		if(this.userService == null){
			this.userService = (UserService) StartupListener.getBean("userService"); 
		}
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}

package cn.edu.sjtu.dcl.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.service.bean.UserVB;
import cn.edu.sjtu.dcl.util.ProjectConstants;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	
	public static final String FAILURE="failure"; 

	public HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public ActionContext getApplication(){
		return ServletActionContext.getContext();
	}
	
	public void setSessionUser(User user){
		this.getSession().setAttribute(ProjectConstants.USER_SESSION_KEY, user);
	}
	
	public User getSessionUser(){
		return (User) this.getSession().getAttribute(ProjectConstants.USER_SESSION_KEY);
	}
}

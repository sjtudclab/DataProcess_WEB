package cn.edu.sjtu.dcl.action;

import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.service.interfaces.UserService;
import cn.edu.sjtu.dcl.util.EncodeUtil;


public class UserAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6740811189158655855L;
	/**
	 * 
	 */
	
	private long id;
	private String name;
	private String email;
	private int role;
	private String password;
	private UserService userService;
	
	public String register(){
		this.getUserService().register(this.generateUser());
		return SUCCESS;
	}

	public User generateUser(){
		User user=new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(EncodeUtil.getMD5Code(password));
		user.setRole(role);
		return user;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}

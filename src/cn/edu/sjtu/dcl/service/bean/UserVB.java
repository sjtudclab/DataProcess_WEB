package cn.edu.sjtu.dcl.service.bean;

import cn.edu.sjtu.dcl.dao.bean.User;

public class UserVB {
	
	private long id;
	private String name;
	private String password;
	private String email;
	private Integer role;
	
	public static UserVB copyValue(User user){
		UserVB uvb=new UserVB();
		uvb.setEmail(user.getEmail());
		uvb.setId(user.getId());
		uvb.setName(user.getName());
		uvb.setPassword(user.getPassword());
		uvb.setRole(user.getRole());
		return uvb;
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
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

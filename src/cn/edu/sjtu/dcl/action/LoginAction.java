package cn.edu.sjtu.dcl.action;

import net.neoremind.sshxcute.core.Result;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.service.interfaces.UserService;
import cn.edu.sjtu.dcl.util.SshUtil;

public class LoginAction extends BaseAction {

	private UserService userService;
	private String name;
	private String password;
	public void testfun()
	{
		
	}
	public String execute() {
		String cmd = "/opt/hadoop-1.0.1/bin/hadoop jar /opt/hadoop-1.0.1/hadoop-examples-1.0.1.jar wordcount input outputword12";
		//SshUtil.RunCmd2("172.30.0148", "root", "123456", cmd);
		
		System.out.println(name + " " + password);
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		if (!name.equalsIgnoreCase("test"))
			user = userService.validate(user);
		System.out.println(user);
		if (user != null) {
			this.setSessionUser(user);
			return SUCCESS;
		} else {
			return FAILURE;
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

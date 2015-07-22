package cn.edu.sjtu.dcl.service.interfaces;

import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.dao.interfaces.UserDAO;
import cn.edu.sjtu.dcl.service.bean.UserVB;


public interface UserService {
	
	public User validate(User user);
	public void register(User user);
	public long findIdByName(String name);
}

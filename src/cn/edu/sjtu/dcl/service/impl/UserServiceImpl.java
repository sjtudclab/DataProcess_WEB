package cn.edu.sjtu.dcl.service.impl;

import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.dao.interfaces.UserDAO;
import cn.edu.sjtu.dcl.service.bean.UserVB;
import cn.edu.sjtu.dcl.service.interfaces.UserService;
import cn.edu.sjtu.dcl.util.EncodeUtil;


public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;

	@Override
	public User validate(User user) {
		// TODO Auto-generated method stub
		User userInfo=userDAO.findByName(user.getName());
		System.out.println(EncodeUtil.getMD5Code(user.getPassword()));
		if(userInfo!=null){
			if(userInfo.getPassword().equals(EncodeUtil.getMD5Code(user.getPassword()))){
				return userInfo;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		this.getUserDAO().save(user);
	}

	@Override
	public long findIdByName(String name) {
		// TODO Auto-generated method stub
		
		return this.getUserDAO().findByName(name).getId();
	}

}

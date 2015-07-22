package cn.edu.sjtu.dcl.dao.interfaces;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dcl.dao.bean.User;

@Transactional
public interface UserDAO extends BaseDAO<User> {
	
	public User findByName(String name);

}

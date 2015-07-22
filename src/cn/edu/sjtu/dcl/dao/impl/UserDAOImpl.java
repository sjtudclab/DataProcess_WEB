package cn.edu.sjtu.dcl.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.dao.interfaces.UserDAO;


@Transactional
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("name", name));
		List<User> userInfo= this.findByCriteria(criteria, 0, 1);
		if(userInfo!=null&&userInfo.size()==1){
			return userInfo.get(0);
		} else {
			return null;
		}
	}

	
}

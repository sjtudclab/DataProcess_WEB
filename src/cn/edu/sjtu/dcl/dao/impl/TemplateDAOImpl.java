package cn.edu.sjtu.dcl.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dcl.dao.bean.Template;
import cn.edu.sjtu.dcl.dao.interfaces.TemplateDAO;


@Transactional
public class TemplateDAOImpl extends BaseDAOImpl<Template> implements
		TemplateDAO {

}

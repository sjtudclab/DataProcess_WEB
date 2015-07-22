package cn.edu.sjtu.dcl.dao.interfaces;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dcl.dao.bean.Template;

@Transactional
public interface TemplateDAO extends BaseDAO<Template> {

}

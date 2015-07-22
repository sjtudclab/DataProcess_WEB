package cn.edu.sjtu.dcl.dao.interfaces;

import java.util.Vector;

import cn.edu.sjtu.dcl.dao.bean.Template;


public interface IDB_Template
{
	public Template get(String name) throws Exception;
	public Template get(long id) throws Exception;
	public Vector<Template> getAll() throws Exception;
	public void addTemplate(Template mrt) throws Exception;
	public void deleteTemplate(long id) throws Exception;
	public void updateTemplate(Template mrt) throws Exception;
}

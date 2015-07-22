package cn.edu.sjtu.dcl.service.interfaces;

import java.util.List;
import java.util.Map;

import cn.edu.sjtu.dcl.dao.bean.Template;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.util.Page;


public interface TemplateService {

	public Template getTemplateById(long id);

	public void createTemp(Template temp);
	
	public void updateTemp(Template temp);

	public boolean deleteTemp(long templateId, User user);
	
	Page getPage(int pno, int pageSize, Map options);

}

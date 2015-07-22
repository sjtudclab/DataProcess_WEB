package cn.edu.sjtu.dcl.service.impl;

import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.edu.sjtu.dcl.dao.bean.Template;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.dao.interfaces.TemplateDAO;
import cn.edu.sjtu.dcl.service.interfaces.TemplateService;
import cn.edu.sjtu.dcl.util.Page;
import cn.edu.sjtu.dcl.util.SearchUtil;


public class TemplateServiceImpl implements TemplateService {
	
	private TemplateDAO tempDAO;

	@Override
	public Template getTemplateById(long id) {
		// TODO Auto-generated method stub
		return this.getTempDAO().get(id);
	}

	@Override
	public void createTemp(Template temp) {
		// TODO Auto-generated method stub
		this.getTempDAO().save(temp);
	}

	@Override
	public boolean deleteTemp(long templateId, User user) {
		// TODO Auto-generated method stub
		if(this.getTempDAO().get(templateId).getDeveloper().getId()==user.getId()){
			this.getTempDAO().delete(templateId);
			return true;
		}
		return false;
	}



	public TemplateDAO getTempDAO() {
		return tempDAO;
	}

	public void setTempDAO(TemplateDAO tempDAO) {
		this.tempDAO = tempDAO;
	}

	@Override
	public Page getPage(int pno, int pageSize, Map options) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Template.class);
		if (options != null)   
        {   
            Set<Integer> keys = options.keySet();   
            for (Integer key : keys)   
            {   
            	if(key.equals(new Integer(1))){
            		System.out.println((String) options.get(key));
            		criteria.add(Restrictions.ilike(SearchUtil.tempMap.get(key), (String) options.get(key),MatchMode.ANYWHERE));
            	} else if(key.equals(new Integer(2))){
            		criteria.createCriteria(SearchUtil.tempMap.get(key),Criteria.LEFT_JOIN).add(Restrictions.ilike("name", (String) options.get(key), MatchMode.ANYWHERE));
            	}
            }
        }
		return tempDAO.findPageByCreiteria(pno, pageSize, criteria);
	}

	@Override
	public void updateTemp(Template temp) {
		// TODO Auto-generated method stub
		tempDAO.update(temp);
	}

}

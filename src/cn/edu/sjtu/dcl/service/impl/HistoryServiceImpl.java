package cn.edu.sjtu.dcl.service.impl;

import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.edu.sjtu.dcl.dao.bean.History;
import cn.edu.sjtu.dcl.dao.bean.Template;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.dao.interfaces.HistoryDAO;
import cn.edu.sjtu.dcl.service.interfaces.HistoryService;
import cn.edu.sjtu.dcl.util.Page;
import cn.edu.sjtu.dcl.util.SearchUtil;


public class HistoryServiceImpl implements HistoryService {
	
	private HistoryDAO historyDAO;

	@Override
	public History getHistoryById(long id) {
		// TODO Auto-generated method stub
		return historyDAO.get(id);
	}

	@Override
	public boolean deleteHistory(long historyId, User user) {
		// TODO Auto-generated method stub
		if(this.getHistoryDAO().get(historyId).getUser().getId()==user.getId()){
			this.getHistoryDAO().delete(historyId);
			return true;
		}
		return false;
	}

	@Override
	public Page getPage(int pno, int pageSize, Map options) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(History.class);
		if (options != null)   
        {   
            Set<Integer> keys = options.keySet();   
            for (Integer key : keys)   
            {   
            	if(key.equals(new Integer(1))){
            		System.out.println((String) options.get(key));
            		criteria.add(Restrictions.ilike(SearchUtil.historyMap.get(key), (String) options.get(key),MatchMode.ANYWHERE));
            	} else if(key.equals(new Integer(2))){
            		criteria.createCriteria(SearchUtil.historyMap.get(key),Criteria.LEFT_JOIN).add(Restrictions.ilike("name", (String) options.get(key), MatchMode.ANYWHERE));
            	}
            }
        }
		return historyDAO.findPageByCreiteria(pno, pageSize, criteria);
	}

	public HistoryDAO getHistoryDAO() {
		return historyDAO;
	}

	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}

}

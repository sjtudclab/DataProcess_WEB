package cn.edu.sjtu.dcl.service.interfaces;

import java.util.Map;

import cn.edu.sjtu.dcl.dao.bean.History;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.util.Page;


public interface HistoryService {
	
	public History getHistoryById(long id);

	public boolean deleteHistory(long historyId, User user);
	
	Page getPage(int pno, int pageSize, Map options);

}

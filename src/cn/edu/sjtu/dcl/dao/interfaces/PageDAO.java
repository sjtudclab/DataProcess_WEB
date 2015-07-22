package cn.edu.sjtu.dcl.dao.interfaces;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dcl.util.Page;

@Transactional
public interface PageDAO extends BaseDAO<Page> {
	
	public void init(int start,String tableName,List<String> options);
	 public Page getPage();
	 public void searchInit(int start, String tableName,HashMap<Integer, Object> options);

}

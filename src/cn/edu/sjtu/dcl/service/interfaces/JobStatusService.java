package cn.edu.sjtu.dcl.service.interfaces;

import java.util.List;
import java.util.Map;

import cn.edu.sjtu.dcl.dao.bean.JobStatus;
import cn.edu.sjtu.dcl.util.Page;

public interface JobStatusService {

	/**
	 * @param args
	 */

	public Page getPage(int pno, int pageSize, Map options);

}

package cn.edu.sjtu.dcl.dao.impl;

import org.jbpm.api.ProcessEngine;

import cn.edu.sjtu.dcl.dao.bean.History;
import cn.edu.sjtu.dcl.dao.interfaces.JbpmDAO;


public class JbpmDAOImpl extends BaseDAOImpl<History> implements JbpmDAO {
	

	private ProcessEngine processEngine;

	public ProcessEngine getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}
}

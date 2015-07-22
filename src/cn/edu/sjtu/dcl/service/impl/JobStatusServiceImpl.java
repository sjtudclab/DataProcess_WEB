package cn.edu.sjtu.dcl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.edu.sjtu.dcl.dao.bean.JobRelation;
import cn.edu.sjtu.dcl.dao.bean.JobStatus;
import cn.edu.sjtu.dcl.dao.interfaces.JobRelationDAO;
import cn.edu.sjtu.dcl.service.interfaces.JobStatusService;
import cn.edu.sjtu.dcl.util.OOZIEUtil;
import cn.edu.sjtu.dcl.util.Page;

public class JobStatusServiceImpl implements JobStatusService {

	private JobRelationDAO jobRelationDAO;

	@Override
	public Page getPage(int pno, int pageSize, Map options) {
		// TODO Auto-generated method stub

		DetachedCriteria criteria = DetachedCriteria
				.forClass(JobRelation.class);
		criteria.add(Restrictions.eq("ownerId", options.get(1)));

		Page page = jobRelationDAO.findPageByCreiteria(pno, pageSize, criteria);

		List<JobRelation> jobRelations = page.getList();
		
		System.out.println("in jobStatusServiceImpl##############################");
		
		for(JobRelation jobRelation : jobRelations)
		{
			System.out.println(jobRelation.getJobIdOnHadoop());
		}
		
		List<JobStatus> jobStatusList = new ArrayList<JobStatus>();
		//try {
			for (JobRelation jobRelation : jobRelations) {
				try{
				jobStatusList.add(OOZIEUtil.getJobStatus(jobRelation
						.getJobIdOnHadoop()));
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		page.setList(jobStatusList);
		
		System.out.println("in jobStatusServiceImpl---------------------");

		return page;
	}

	public JobRelationDAO getJobRelationDAO() {
		return jobRelationDAO;
	}

	public void setJobRelationDAO(JobRelationDAO jobRelationDAO) {
		this.jobRelationDAO = jobRelationDAO;
	}

}

package cn.edu.sjtu.dcl.action;

import java.util.HashMap;
import java.util.List;

import cn.edu.sjtu.dcl.dao.bean.JobStatus;
import cn.edu.sjtu.dcl.service.interfaces.JobStatusService;
import cn.edu.sjtu.dcl.util.Page;

public class JobStatusAction extends BaseAction {

	private static final long serialVersionUID = -507517356111005286L;

	private JobStatusService jobStatusService;
	
	private int pno;
	private int pageSize = 10;
	
	public JobStatusService getJobStatusService()
	{
		return this.jobStatusService;
	}
	
	public void setJobStatusService(JobStatusService jobStatusService)
	{
		this.jobStatusService = jobStatusService;
	}
	
	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getJobStatus() {
		if (pno <= 0) {
			this.setPno(1);
		}
		
		Page page = null;
		HashMap<Integer, Long> map = new HashMap<Integer, Long>();
		map.put(1, this.getSessionUser().getId());
				
		page = this.getJobStatusService().getPage(pno, pageSize, map);
		
		System.out.println("in jobStatusAction-----------------------------");
		
		this.getRequest().setAttribute("page", page);
		
		List<JobStatus> jobStatusList = page.getList();
		
		System.out.println("in jobStatusAction##############################");
		
		for(JobStatus jobStatus : jobStatusList)
		{
			System.out.println(jobStatus.getJobId());
		}
		
		this.getRequest().setAttribute("list", jobStatusList);
		
		return SUCCESS;
	}

}

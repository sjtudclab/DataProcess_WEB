package cn.edu.sjtu.dcl.dao.bean;

import java.sql.Date;

public class JobStatusInfo {
	
	enum JobStatusCode
	{
		SUBMITTEDFAILED, SUBMITTED, RUNNING, SUCCESS, FAILED
	}
	
	private long jobId;
	private JobStatusCode status;
	private Date startedTime;
	private Date endTime;
	
	public long getJobId()
	{
		return this.jobId;
	}
	
	public void setJobId(long jobId)
	{
		this.jobId = jobId;
	}
	
	public JobStatusCode getJobStatus()
	{
		return this.status;
	}
	
	public void setJobStatus(JobStatusCode status)
	{
		this.status = status;
	}
	
	public Date getStartedTime()
	{
		return this.startedTime;
	}
	
	public void setStartedTime(Date startedTime)
	{
		this.startedTime = startedTime;
	}
	
	public Date getEndTime()
	{
		return this.endTime;
	}
	
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}
}

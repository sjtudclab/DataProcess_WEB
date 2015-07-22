package cn.edu.sjtu.dcl.dao.bean;

import java.util.Date;

public class JobStatus {

	private String jobId;
	private Date startTime;
	private Date endTime;
	private Date createTime;
	private Status statusCode;

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Status getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(Status statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		
		System.out.println("before switch in getStatus of JobSstatus+++++++++++++++++++");
		
		switch (this.statusCode) {
		case FAILED:
			return "failed";
		case KILLED:
			return "killed";
		case SUSPENDED:
			return "suspended";
		case SUCCEEDED:
			return "succeeded";
		case RUNNING:
			return "running";
		case PREP:
			return "prep";
		}

		System.out.println("after switch in getStatus of JobStatus+++++++++++++++++++");
		
		return "unkown";
	}

	public enum Status {
		FAILED, KILLED, SUSPENDED, SUCCEEDED, RUNNING, PREP
	}
}

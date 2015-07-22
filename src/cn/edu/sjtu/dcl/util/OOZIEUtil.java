package cn.edu.sjtu.dcl.util;


import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.WorkflowJob;
import org.jboss.resteasy.spi.NotFoundException;

import cn.edu.sjtu.dcl.dao.bean.JobStatus;
import cn.edu.sjtu.dcl.dao.bean.JobStatus.Status;

public class OOZIEUtil {

	private final static String OOZIECONF = "./oozie.conf";
	
	public static JobStatus getJobStatus(String jobId)
			throws Exception {

		if (null == jobId || jobId.isEmpty()) {
			throw new IllegalArgumentException(
					"jobId id should not be negative");
		}
		
		String oozieConfigFile = OOZIEUtil.class.getResource("/").toString() + OOZIECONF;
		
		System.out.println(oozieConfigFile);
		
		if(oozieConfigFile.startsWith("file:/"))
		{
			oozieConfigFile = oozieConfigFile.substring("file:".length());
		}
		
		System.out.println(oozieConfigFile);
		
		String oozieUrl = PropertiesHandler.getValue(oozieConfigFile, "url");

		try {
			OozieClient wc = new OozieClient(oozieUrl);
			WorkflowJob job = wc.getJobInfo(jobId);
			
			if (null == job) {
				throw new NotFoundException("the job:" + jobId + " not found");
			}

			JobStatus jobStatus = new JobStatus();
			jobStatus.setJobId(jobId);
			jobStatus.setStartTime(job.getStartTime());
			jobStatus.setEndTime(job.getEndTime());
			jobStatus.setCreateTime(job.getCreatedTime());
			
			System.out.println("before switch in getJobStatus+++++++++++++++++++");
			
			switch (job.getStatus()) {
			case FAILED:
				jobStatus.setStatusCode(Status.FAILED);
				break;
			case KILLED:
				jobStatus.setStatusCode(Status.KILLED);
				break;
			case SUCCEEDED:
				jobStatus.setStatusCode(Status.SUCCEEDED);
				break;
			case SUSPENDED:
				jobStatus.setStatusCode(Status.SUSPENDED);
				break;
			case RUNNING:
				jobStatus.setStatusCode(Status.RUNNING);
				break;
			case PREP:
				jobStatus.setStatusCode(Status.PREP);
				break;
			}

			System.out.println("after switch in getJobStatus+++++++++++++++++++");
			
			return jobStatus;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}

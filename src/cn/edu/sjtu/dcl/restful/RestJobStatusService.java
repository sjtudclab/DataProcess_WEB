package cn.edu.sjtu.dcl.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import cn.edu.sjtu.dcl.dao.bean.JobStatus;
import cn.edu.sjtu.dcl.rest.exception.RestInnerException;
import cn.edu.sjtu.dcl.util.OOZIEUtil;

@Path("jobStatus")
public class RestJobStatusService {

	@GET
	@Path("byId/{jobId}")
	@Produces("application/json")
	public JobStatus getJobStatus(@PathParam("jobId") String jobId)
			throws RestInnerException {

		if (null == jobId || jobId.isEmpty()) {
			throw new IllegalArgumentException(
					"jobId id should not be negative");
		}
		try {
			return OOZIEUtil.getJobStatus(jobId);
		} catch (Exception e) {
			throw new RestInnerException("error when get job status:" + jobId,
					e);
		}
	}
}

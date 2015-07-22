package cn.edu.sjtu.dcl.restful;

import java.util.List;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.impl.DB_Job;
import cn.edu.sjtu.dcl.rest.exception.RestInnerException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import org.jboss.resteasy.spi.NotFoundException;

@Path("job")
public class RestJobService {

	@GET
	@Path("byId/{id}")
	@Produces("application/json")
	public Job GetJobById(@PathParam("id") long id) throws RestInnerException {
		if (id < 0) {
			throw new IllegalArgumentException("id should not be negative");
		}

		Job job = null;

		try {
			DB_Job db = new DB_Job();
			job = db.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RestInnerException(
					"inner error when get job by id:" + id, e);
		}

		if (null == job) {
			throw new NotFoundException("job with id:" + id + " not found");
		}

		return job;
	}

	@GET
	@Path("all")
	@Produces("application/json")
	public List<Job> GetAllJob() throws RestInnerException {
		List<Job> jobs = null;
		try {
			DB_Job db = new DB_Job();
			jobs = db.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RestInnerException("inner error when get all jobs", e);
		}

		return jobs;
	}

	@GET
	@Path("byDeveloper/{developerId}")
	@Produces("application/json")
	public List<Job> GetJobsByUser(@PathParam("developerId") long developerId)
			throws RestInnerException {
		if (developerId < 0) {
			throw new IllegalArgumentException("userId should not be negative");
		}

		List<Job> jobs = null;
		try {
			DB_Job db = new DB_Job();
			jobs = db.getJobsByDeveloper(developerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RestInnerException("inner error when get jobs by user:"
					+ developerId, e);
		}

		return jobs;
	}

	@POST
	@Path("exec/{jobId}")
	@Produces("application/json")
	public void ExecuteJob(@PathParam("jobId") long jobId) {

	}

}

package cn.edu.sjtu.dcl.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import cn.edu.sjtu.dcl.dao.bean.Job;

public interface IDB_Job
{
	public Job get(String jobname) throws SQLException, Exception;
	public Job get(long jobid) throws SQLException, Exception;
	public List<Job> getAll() throws SQLException, Exception;
	public List<Job> getJobsByDeveloper(long developerId) throws SQLException, Exception;
	public void addJob(Job mrj);
	public void deleteJob(long id);
	public void updateJob(Job mrj);
}

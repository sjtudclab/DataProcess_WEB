package cn.edu.sjtu.dcl.service.interfaces;

import java.util.List;
import java.util.Map;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.util.Page;


public interface JobService {


	public Job getJobById(long id);

	public void createJob(Job job);
	
	public void updateJob(Job job);

	public boolean deleteJob(long jobId, User user);

	Page getPage(int pno, int pageSize, Map options);
	
	public List<Job> getAll();


}

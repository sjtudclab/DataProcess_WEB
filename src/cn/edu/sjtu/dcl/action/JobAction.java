package cn.edu.sjtu.dcl.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.service.interfaces.JobService;
import cn.edu.sjtu.dcl.service.interfaces.UserService;
import cn.edu.sjtu.dcl.util.Page;
import cn.edu.sjtu.dcl.util.SearchUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

@Path(value = "/question")
@Produces("application/*;charset=UTF-8")
@Consumes("application/*;charset=utf-8")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JobAction extends BaseAction {

	private long id;
	private String name;
	private String path;
	private String mapper;
	private String reducer;
	private String inputFormat;
	private String mapInputValue;
	private String mapInputKey;
	private String mapOutputKey;
	private String mapOutputValue;
	private String outputKey;
	private String outputValue;
	private boolean iterative;
	private boolean mapType;
	private String parameters;
	private String description;
	private long developerId;
	private Date uploadTime;
	private int pno;
	private int pageSize = 10;
	private int searchKey;
	private String searchValue;
	private JobService jobService;
	private UserService userService;
	private HashMap<String, Object> ret = new HashMap<String, Object>();

	public String getJobs_http() {
		List<Job> jobs = this.jobService.getAll();
		ret.put("jobs", jobs);
		return SUCCESS;
	}

	public String getJobList() {
		System.out.println("in get job list+++++++++++++++++++++++++");
		if (pno <= 0) {
			this.setPno(1);
		}
		Page page = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		if (this.getSearchValue() != null && !this.getSearchValue().equals("")) {
			System.out.println("in map");
			map.put(searchKey, this.getSearchValue());

			this.getRequest().setAttribute(
					"searchString",
					"&searchKey=" + this.getSearchKey() + "&searchValue="
							+ this.getSearchValue());

			page = this.getJobService().getPage(pno, pageSize, map);
		} else {
			page = this.getJobService().getPage(pno, pageSize, null);
		}
		this.setSearchValue(null);
		this.getRequest().setAttribute("page", page);
		List jobList = page.getList();
		this.getRequest().setAttribute("list", jobList);
		return SUCCESS;
	}

	public String getJobById() {
		this.getRequest().setAttribute("job",
				this.getJobService().getJobById(id));
		this.getRequest().setAttribute("developer",
				this.getJobService().getJobById(id).getDeveloper());
		return SUCCESS;
	}
	
	public String getModifyInfo() {
		this.getRequest().setAttribute("job",
				this.getJobService().getJobById(id));
		return SUCCESS;
	}

	public String createJob() {
		Job job = generateJob();
		this.getJobService().createJob(job);
		return SUCCESS;
	}
	
	public String updateJob() {
		Job job = modifyInfo(id);
		this.getJobService().updateJob(job);
		return SUCCESS;
	}

	public String deleteJob() {
		if (this.getJobService().deleteJob(id, getSessionUser())) {
			return SUCCESS;
		} else
			return FAILURE;
	}

	public String getPersonalJob() {
		if (pno <= 0) {
			this.setPno(1);
		}
		Page page = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(3, this.getSessionUser().getName());
		if (this.getSearchValue() != null && !this.getSearchValue().equals("")) {
			System.out.println("in map");
			map.put(searchKey, this.getSearchValue());

			this.getRequest().setAttribute(
					"searchString",
					"&searchKey=" + this.getSearchKey() + "&searchValue="
							+ this.getSearchValue());

		}
		page = this.getJobService().getPage(pno, pageSize, map);
		this.setSearchValue(null);
		this.getRequest().setAttribute("page", page);
		List jobList = page.getList();
		this.getRequest().setAttribute("list", jobList);
		return SUCCESS;
	}

	public String createTrans() {
		this.getRequest().setAttribute("path", path);
		System.out.println(this.getRequest().getAttribute("path"));
		return SUCCESS;
	}

	public Job generateJob() {
		Job job = new Job();
		System.out.println("--------------------" + path);
		job.setPath(path);
		job.setDescription(description);
		job.setDeveloper(this.getSessionUser());
		job.setInputFormat(inputFormat);
		job.setIterative(iterative);
		job.setMapType(mapType);
		job.setMapInputKey(mapInputKey);
		job.setMapInputValue(mapInputValue);
		job.setMapOutputKey(mapOutputKey);
		job.setMapOutputValue(mapOutputValue);
		job.setMapper(mapper);
		job.setName(name);
		job.setUploadTime(new Date());
		job.setReducer(reducer);
		job.setParameters(parameters);
		job.setOutputValue(mapOutputValue);
		job.setOutputKey(mapOutputKey);
		return job;
	}
	
	public Job modifyInfo(long id) {
		Job job = jobService.getJobById(id);
		System.out.println("--------------------" + path);
		job.setDescription(description);
		job.setDeveloper(this.getSessionUser());
		job.setInputFormat(inputFormat);
		job.setIterative(iterative);
		job.setMapType(mapType);
		job.setMapInputKey(mapInputKey);
		job.setMapInputValue(mapInputValue);
		job.setMapOutputKey(mapOutputKey);
		job.setMapOutputValue(mapOutputValue);
		job.setMapper(mapper);
		job.setName(name);
		job.setUploadTime(new Date());
		job.setReducer(reducer);
		job.setParameters(parameters);
		job.setOutputValue(mapOutputValue);
		job.setOutputKey(mapOutputKey);
		return job;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMapper() {
		return mapper;
	}

	public void setMapper(String mapper) {
		this.mapper = mapper;
	}

	public String getReducer() {
		return reducer;
	}

	public void setReducer(String reducer) {
		this.reducer = reducer;
	}

	public String getInputFormat() {
		return inputFormat;
	}

	public void setInputFormat(String inputFormat) {
		this.inputFormat = inputFormat;
	}

	public String getMapInputValue() {
		return mapInputValue;
	}

	public void setMapInputValue(String mapInputValue) {
		this.mapInputValue = mapInputValue;
	}

	public String getMapInputKey() {
		return mapInputKey;
	}

	public void setMapInputKey(String mapInputKey) {
		this.mapInputKey = mapInputKey;
	}

	public String getMapOutputKey() {
		return mapOutputKey;
	}

	public void setMapOutputKey(String mapOutputKey) {
		this.mapOutputKey = mapOutputKey;
	}

	public String getMapOutputValue() {
		return mapOutputValue;
	}

	public void setMapOutputValue(String mapOutputValue) {
		this.mapOutputValue = mapOutputValue;
	}

	public String getOutputKey() {
		return outputKey;
	}

	public void setOutputKey(String outputKey) {
		this.outputKey = outputKey;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(long developerId) {
		this.developerId = developerId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(String outputValue) {
		this.outputValue = outputValue;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
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

	public int getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(int searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean isMapType() {
		return mapType;
	}

	public void setMapType(boolean mapType) {
		this.mapType = mapType;
	}

	public boolean isIterative() {
		return iterative;
	}

	public void setIterative(boolean iterative) {
		this.iterative = iterative;
	}

	public HashMap<String, Object> getRet() {
		return ret;
	}

	public void setRet(HashMap<String, Object> ret) {
		this.ret = ret;
	}

}

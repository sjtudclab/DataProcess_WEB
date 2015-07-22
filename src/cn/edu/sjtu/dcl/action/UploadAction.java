package cn.edu.sjtu.dcl.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.service.interfaces.JobService;

public class UploadAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File file;
	
	@SuppressWarnings("unused")
	private String contentType;
	
	@SuppressWarnings("unused")
	private String filename;

	private String name;
	private String description;
	
	private String localPath;
	
	private JobService jobService;
	
	private final static int BUFFER_SIZE = 1024*1024;
	
	public void setUpload(File file) {
		System.out.println("in setUpload");
		this.file = file;
	}

	/*
	public File getUpload() {
		return this.file;
	}
	*/

	public void setUploadContentType(String contentType) {
		System.out.println("in setUploadContentType");
		this.contentType = contentType;
	}

	/**
	public String getUploadContentType() {
		return this.contentType;
	}
	*/

	public void setUploadFileName(String filename) {
		System.out.println("in setUploadFileName");
		this.filename = filename;
	}
	
	public void setName(String jobName) {
		this.name = jobName;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
	
	/**
	public String getUploadFileName() {
		return this.filename;
	}
	*/

	public String upload() {
		this.getResponse().setContentType("text/html;charset=UTF-8");
		File uploadDir = new File(this.getRequest().getRealPath("/")
				+ "jar_file");
		// File inputFile = new File(file);
		if (!uploadDir.exists()) {// �������򴴽�
			uploadDir.mkdirs();
		}
		String name = System.currentTimeMillis() + ".jar";

		localPath = uploadDir + File.separator + name;

		System.out.println("====================:" + localPath);

		try {
			FileInputStream fis;
			fis = new FileInputStream(file);

			FileOutputStream fos = new FileOutputStream(new File(localPath));
			byte[] buffer = new byte[BUFFER_SIZE];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			fis.close();
			fos.close();
			
			createJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	private void createJob() {
		Job job = new Job();
		job.setDescription(description);
		job.setName(name);
		job.setDeveloper(getSessionUser());
		job.setUploadTime(new Date());
		job.setPath(localPath);
		this.getJobService().createJob(job);
	}
	
	@Override
	public void addActionError(String errorMessage)
	{
		System.out.println("errorMessage:" + errorMessage);
		super.addActionError(errorMessage);
	}
	
	@Override
	public void addActionMessage(String message)
	{
		System.out.println("message:" + message);
		super.addActionMessage(message);
	}
	
	@Override
	public void addFieldError(String fieldName, String errorMessage)
	{
		System.out.println("fieldName:" + fieldName + " error:" + errorMessage);
		super.addFieldError(fieldName, errorMessage);
	}

	// public void setFile(String file) {
	// this.file = file;
	// }

	/**
	 * public String getLocalPath() { return localPath; }
	 * 
	 * public void setLocalPath(String localPath) { this.localPath = localPath;
	 * }
	 */

}

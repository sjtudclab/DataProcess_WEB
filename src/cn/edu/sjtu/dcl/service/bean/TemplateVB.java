package cn.edu.sjtu.dcl.service.bean;

import java.util.Date;


public class TemplateVB {
	
	private long id;
	private String name;
	private String description;
	private String path;
	private long developerId;
	private Date uploadTime;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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

}

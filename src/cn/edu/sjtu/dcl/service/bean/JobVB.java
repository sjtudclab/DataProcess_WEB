package cn.edu.sjtu.dcl.service.bean;

import java.util.Date;


public class JobVB {
	
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
	private Integer iterative;
	private String parameters;
	private String description;
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
	public Integer getIterative() {
		return iterative;
	}
	public void setIterative(Integer iterative) {
		this.iterative = iterative;
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

}

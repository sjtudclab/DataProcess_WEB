package cn.edu.sjtu.dcl.dao.bean;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DATA_PROCESS_JOB_TABLE")
public class Job {

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
	private boolean isIterative;
	private boolean isMapType;
	private String parameters;
	private String description;
	private User developer;
	private Date uploadTime;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		try {
			return java.net.URLEncoder.encode(name.toString(),
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PATH")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "MAPPER")
	public String getMapper() {
		return mapper;
	}

	public void setMapper(String mapper) {
		this.mapper = mapper;
	}

	@Column(name = "REDUCER")
	public String getReducer() {
		return reducer;
	}

	public void setReducer(String reducer) {
		this.reducer = reducer;
	}

	@Column(name = "INPUT_FORMAT")
	public String getInputFormat() {
		return inputFormat;
	}

	public void setInputFormat(String inputFormat) {
		this.inputFormat = inputFormat;
	}

	@Column(name = "MAP_INPUT_VALUE")
	public String getMapInputValue() {
		return mapInputValue;
	}

	public void setMapInputValue(String mapInputValue) {
		this.mapInputValue = mapInputValue;
	}

	@Column(name = "MAP_INPUT_KEY")
	public String getMapInputKey() {
		return mapInputKey;
	}

	public void setMapInputKey(String mapInputKey) {
		this.mapInputKey = mapInputKey;
	}

	@Column(name = "MAP_OUTPUT_KEY")
	public String getMapOutputKey() {
		return mapOutputKey;
	}

	public void setMapOutputKey(String mapOutputKey) {
		this.mapOutputKey = mapOutputKey;
	}

	@Column(name = "MAP_OUTPUT_VALUE")
	public String getMapOutputValue() {
		return mapOutputValue;
	}

	public void setMapOutputValue(String mapOutputValue) {
		this.mapOutputValue = mapOutputValue;
	}

	@Column(name = "OUTPUT_KEY")
	public String getOutputKey() {
		return outputKey;
	}

	public void setOutputKey(String outputKey) {
		this.outputKey = outputKey;
	}

	@Column(name = "PARAMETERS")
	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "DEVELOPER_ID")
	public User getDeveloper() {
		return developer;
	}

	public void setDeveloper(User developer) {
		this.developer = developer;
	}

	@Column(name = "UPLOAD_TIME")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name = "OUTPUT_VALUE")
	public String getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(String outputValue) {
		this.outputValue = outputValue;
	}

	@Column(name = "ITERATIVE")
	public boolean isIterative() {
		return isIterative;
	}

	public void setIterative(boolean isIterative) {
		this.isIterative = isIterative;
	}

	@Column(name = "MAPTYPE")
	public boolean isMapType() {
		return isMapType;
	}

	public void setMapType(boolean isMapType) {
		this.isMapType = isMapType;
	}
}

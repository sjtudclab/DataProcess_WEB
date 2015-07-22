package cn.edu.sjtu.dcl.dao.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Table(name="DATA_PROCESS_MODEL_RELATION_TABLE")
public class ModelRelation {
	
	private long id;
	private Job job;
	private long modelId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="MODEL_ID")
	public long getModelId() {
		return modelId;
	}
	public void setModelId(long modelId) {
		this.modelId = modelId;
	}
	
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}

}

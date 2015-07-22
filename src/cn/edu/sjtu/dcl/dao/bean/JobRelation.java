package cn.edu.sjtu.dcl.dao.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DATA_PROCESS_JOBRELATION_TABLE")
public class JobRelation {
	
	private long id;
	private long templateId;
	private String jobIdOnHadoop;
	private long ownerId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="TemplateID")
	public long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}
	
	@Column(name="JobIdOnHadoop")
	public String getJobIdOnHadoop()
	{
		return this.jobIdOnHadoop;
	}
	public void setJobIdOnHadoop(String jobIdOnHadoop)
	{
		this.jobIdOnHadoop = jobIdOnHadoop;
	}
	
	@Column(name="ownerId")
	public long getOwnerId() {
		return this.ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
}

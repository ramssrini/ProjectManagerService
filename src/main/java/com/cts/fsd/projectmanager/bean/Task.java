package com.cts.fsd.projectmanager.bean;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection  = "task")
public class Task implements java.io.Serializable{
	    
    private String _id;
    private String parentId;
    private String projectId;
    @Field(value="taskDesc")
    private String task;
    private String startDate;
	private String endDate;
    private int priority;
    private String status;
	
	public Task() {
		
	}
    
    public Task(String parentId,String projectId, String task,  String startDate,
			String endDate, int priority) {
		super();
		this.parentId = parentId;
		this.projectId = projectId;
		this.task = task;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
	}

	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String taskDesc) {
		this.task = taskDesc;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}   
}

package com.cts.fsd.projectmanager.bean;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection  = "project")
public class Project implements Serializable{

	
    private long projectId;
	private String project;
	private String startDate;
	private String endDate;
    private int priority;
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
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
	
	
	
}

package com.cts.fsd.projectmanager.vo;

public class Task implements java.io.Serializable{
	    
    private String parentId;
    private String projectId;
    private String project;
    private String userId;
    private String task;
    private String taskId;
    private String startDate;
	private String endDate;
    private int priority;
    private String status;
    private String parentTask;
    private String userLastName ;
    private String userFirstName;
    private String userEmployeeId;
    private String userprojectId;
    private String userTaskId;
	
	public Task() {
		
	}
    
    public Task(String parentId, String projectId, String task,  String startDate,
			String endDate, int priority, String userId, String parentTask, 
			String userLastName , String userFirstName, String userEmployeeId, 
			String userprojectId, String userTaskId
			) {
		super();
		this.parentId = parentId;
		this.projectId = projectId;
		this.task = task;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.userId = userId;
		this.parentTask = parentTask;
		this.userEmployeeId = userEmployeeId;
		this.userFirstName = userFirstName;
		this.setUserLastName(userLastName);
		this.userprojectId = userprojectId;
		this.userTaskId = userTaskId;
	}

	
	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserEmployeeId() {
		return userEmployeeId;
	}

	public void setUserEmployeeId(String userEmployeeId) {
		this.userEmployeeId = userEmployeeId;
	}

	public String getUserprojectId() {
		return userprojectId;
	}

	public void setUserprojectId(String userprojectId) {
		this.userprojectId = userprojectId;
	}

	public String getUserTaskId() {
		return userTaskId;
	}

	public void setUserTaskId(String userTaskId) {
		this.userTaskId = userTaskId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the parentTask
	 */
	public String getParentTask() {
		return parentTask;
	}

	/**
	 * @param parentTask the parentTask to set
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * @param userLastName the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

    
   
}

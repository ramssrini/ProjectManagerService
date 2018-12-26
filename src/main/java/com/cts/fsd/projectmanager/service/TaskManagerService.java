package com.cts.fsd.projectmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cts.fsd.projectmanager.bean.ParentTask;
import com.cts.fsd.projectmanager.bean.Project;
import com.cts.fsd.projectmanager.bean.Task;
import com.cts.fsd.projectmanager.bean.User;
import com.cts.fsd.projectmanager.utils.Utils;
import com.mongodb.client.result.DeleteResult;
@Service
public class TaskManagerService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	
	
	public com.cts.fsd.projectmanager.vo.Task addTask(com.cts.fsd.projectmanager.vo.Task taskReq) {		
		// Adding Task data
		Task task = new Task(taskReq.getParentId(),taskReq.getProjectId(), taskReq.getTask(),taskReq.getStartDate(), taskReq.getEndDate(), taskReq.getPriority());
		task.set_id(Utils.getNextSequence("taskid").toString());
		mongoTemplate.save(task);
		
		User user = new User();
		user.setEmployeeId(taskReq.getUserEmployeeId());
		user.setLastName(taskReq.getUserLastName());
		user.setFirstName(taskReq.getUserFirstName());
		user.setProjectId(taskReq.getUserprojectId());
		user.setTaskId(task.get_id());
		user.set_id(taskReq.getUserId());
		Query queryUser = new Query();
		queryUser.addCriteria(Criteria.where("_id").is(taskReq.getUserId()));
		mongoTemplate.remove(queryUser, User.class);
		mongoTemplate.save(user);
		
		return taskReq;
	}
	
	public com.cts.fsd.projectmanager.vo.Task addParentTask(com.cts.fsd.projectmanager.vo.Task taskReq) {
		
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTask(taskReq.getParentTask());
		parentTask.set_id(Utils.getNextSequence("parentTaskid").toString());
		
		mongoTemplate.save(parentTask);
		
		return taskReq;
	}
	
	
//	private ParentTask getExistingParentTask(String parentTaskDesc)
//	{
//		
//		Query query = new Query();
//		query.addCriteria(Criteria.where("parentTaskDesc").is(parentTaskDesc));
//		ParentTask parentTask = mongoTemplate.findOne(query, ParentTask.class);
//		
//		return parentTask;
//	}
	
//	public ParentTask addParentTask(Task taskReq) {
//		Query query = new Query();
//		query.with(new Sort(Sort.Direction.DESC, "parentId"));
//		query.limit(1);
//		ParentTask parentTask = mongoTemplate.findOne(query, ParentTask.class); 
//		ParentTask parentTaskNew = new ParentTask();
//		parentTaskNew.setParentTaskDesc(taskReq.getParentTask());
//		long id = parentTask != null? parentTask.getParentId() : 0;
//		parentTaskNew.setParentId(id+1); 		
//		mongoTemplate.save(parentTaskNew);
//		return parentTaskNew;
//	}
	
	public List<com.cts.fsd.projectmanager.vo.Task> getAllTasks() {
		List<Task> tasks = new ArrayList<>();
		tasks = mongoTemplate.findAll(Task.class);
		
		List<com.cts.fsd.projectmanager.vo.Task> taskVos = new ArrayList<>();
		List<ParentTask> parentTasks = getAllParentTasks();
		Map<String, ParentTask> parentTaskMap = new HashMap<>();
		
		for(ParentTask parentTask : parentTasks) {
			parentTaskMap.put(parentTask.get_id(), parentTask);
		}
		List<Project> projectList  = mongoTemplate.findAll(Project.class);
		Map<String, String> projectMap = new HashMap<>();
		
		for(Project project : projectList) {
			projectMap.put(project.get_id(), project.getProject());
		}
		
		List<User> userList  = mongoTemplate.findAll(User.class);
		Map<String, User> userMap = new HashMap<>();
		
		for(User user : userList) {
			userMap.put(user.getTaskId(), user);
		}
		
		for(Task taskItem: tasks)
		{
			com.cts.fsd.projectmanager.vo.Task taskVO = new com.cts.fsd.projectmanager.vo.Task();
			taskVO.setEndDate(taskItem.getEndDate());
			taskVO.setStartDate(taskItem.getStartDate());
			taskVO.setParentId(taskItem.getParentId());
			taskVO.setProjectId(taskItem.getProjectId());
			taskVO.setProject(projectMap.get(taskItem.getProjectId()));
			taskVO.setStatus(taskItem.getStatus());
			taskVO.setTask(taskItem.getTask());
			taskVO.setParentTask(parentTaskMap.get(taskItem.getParentId()).getParentTask());
			taskVO.setPriority(taskItem.getPriority());
			taskVO.setTaskId(taskItem.get_id());
			User user = userMap.get(taskItem.get_id());
			if(user != null)
			{
				taskVO.setUserEmployeeId(user.getEmployeeId());
				taskVO.setUserFirstName(user.getFirstName());
				taskVO.setUserLastName(user.getLastName());
				taskVO.setUserprojectId(user.getProjectId());
				taskVO.setUserId(user.get_id());
			}
			taskVos.add(taskVO);	
		}
		
		
		return taskVos;
	}
	
	public List<ParentTask> getAllParentTasks() {
		List<ParentTask> tasks = new ArrayList<ParentTask>();
		tasks = mongoTemplate.findAll(ParentTask.class);
		
		return tasks;
	}
	
	public com.cts.fsd.projectmanager.vo.Task getTasksById(String id) {
		Task task = new Task();
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		task = mongoTemplate.findOne(query, Task.class);
		com.cts.fsd.projectmanager.vo.Task taskRes = new com.cts.fsd.projectmanager.vo.Task();
		taskRes.setEndDate(task.getEndDate());
		taskRes.setStartDate(task.getStartDate());
		taskRes.setStatus(task.getStatus());
		taskRes.setTask(task.getTask());
		taskRes.setTaskId(task.get_id());
		taskRes.setPriority(task.getPriority());
		taskRes.setProjectId(task.getProjectId());
		taskRes.setParentId(task.getParentId());
		
		
		Query queryParentTask = new Query();
		queryParentTask.addCriteria(Criteria.where("_id").is(taskRes.getParentId()));
		ParentTask parentTask = mongoTemplate.findOne(queryParentTask, ParentTask.class);
		if(parentTask != null)
		{
			taskRes.setParentTask(parentTask.getParentTask());
		}
		
		Query queryProjectTask = new Query();
		queryProjectTask.addCriteria(Criteria.where("_id").is(taskRes.getProjectId()));
		Project project = mongoTemplate.findOne(queryProjectTask, Project.class);
		if(project != null)
		{
			taskRes.setProject(project.getProject());
		}
		
		List<User> userList  = mongoTemplate.findAll(User.class);
		
		for(User user : userList) {
			if(user != null)
			{
				taskRes.setUserEmployeeId(user.getEmployeeId());
				taskRes.setUserFirstName(user.getFirstName());
				taskRes.setUserId(user.get_id());
				taskRes.setUserLastName(user.getLastName());
				taskRes.setUserprojectId(user.getProjectId());
				taskRes.setUserTaskId(user.getTaskId());
			}
		}
		
		return taskRes;
	}
	
	public com.cts.fsd.projectmanager.vo.Task updateTask(com.cts.fsd.projectmanager.vo.Task taskReq)
	{
		// Adding Task data
		Task task = new Task(taskReq.getParentId(),taskReq.getProjectId(), taskReq.getTask(),taskReq.getStartDate(), taskReq.getEndDate(), taskReq.getPriority());
		task.set_id(taskReq.getTaskId());
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(taskReq.getTaskId()));
		mongoTemplate.remove(query, Task.class);	
		mongoTemplate.save(task);
		
		User user = new User();
		user.setEmployeeId(taskReq.getUserEmployeeId());
		user.setLastName(taskReq.getUserLastName());
		user.setFirstName(taskReq.getUserFirstName());
		user.setProjectId(taskReq.getUserprojectId());
		user.setTaskId(task.get_id());
		user.set_id(taskReq.getUserId());
		Query queryUser = new Query();
		queryUser.addCriteria(Criteria.where("_id").is(taskReq.getUserId()));
		mongoTemplate.remove(queryUser, User.class);
		mongoTemplate.save(user);
		return taskReq;
	}
	
	public long deleteTask(String  id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		DeleteResult deleteResult =mongoTemplate.remove(query, Task.class);		
		return deleteResult.getDeletedCount();
	}
}

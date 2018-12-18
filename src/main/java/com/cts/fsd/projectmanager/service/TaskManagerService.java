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
//		ParentTask parentTask = getExistingParentTask(taskReq.getParentTask());
//		if(parentTask == null) {
//			parentTask = addParentTask(taskReq);
//		}
//		Query query = new Query();
//		query.with(new Sort(Sort.Direction.DESC, "taskId"));
//		query.limit(1);
//		Task task = mongoTemplate.findOne(query, Task.class); 
//		long id = task != null ? task.getTaskId():0;
//		taskReq.setTaskId(id+1);
//		taskReq.setParentId(parentTask.getParentId());
		
		// Adding Task data
		Task task = new Task(taskReq.getParentId(),taskReq.getProjectId(), taskReq.getUserId(), taskReq.getTask(),taskReq.getStartDate(), taskReq.getEndDate(), taskReq.getPriority());
		task.set_id(Utils.getNextSequence("taskid").toString());
		mongoTemplate.save(task);
		
		
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
		for(Task taskItem: tasks)
		{
			com.cts.fsd.projectmanager.vo.Task taskVO = new com.cts.fsd.projectmanager.vo.Task();
			taskVO.setEndDate(taskItem.getEndDate());
			taskVO.setStartDate(taskItem.getStartDate());
			taskVO.setParentId(taskItem.getParentId());
			taskVO.setProjectId(taskItem.getProjectId());
			taskVO.setProject(projectMap.get(taskItem.getProjectId()));
			taskVO.setUserId(taskItem.getUserId());
			taskVO.setStatus(taskItem.getStatus());
			taskVO.setTask(taskItem.getTask());
			taskVO.setParentTask(parentTaskMap.get(taskItem.getParentId()).getParentTask());
			taskVO.setPriority(taskItem.getPriority());
			taskVO.setTaskId(taskItem.get_id());
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
		query.addCriteria(Criteria.where("_id").is(Long.valueOf(id)));
		task = mongoTemplate.findOne(query, Task.class);
		com.cts.fsd.projectmanager.vo.Task taskRes = new com.cts.fsd.projectmanager.vo.Task();
		taskRes.setEndDate(task.getEndDate());
		taskRes.setStartDate(task.getStartDate());
		taskRes.setStatus(task.getStatus());
		taskRes.setTask(task.getTask());
		taskRes.setPriority(task.getPriority());
		taskRes.setUserId(task.getUserId());
		taskRes.setProjectId(task.getProjectId());
		taskRes.setParentId(task.getParentId());
		return taskRes;
	}
	
	public com.cts.fsd.projectmanager.vo.Task updateTask(com.cts.fsd.projectmanager.vo.Task taskReq)
	{
//		ParentTask parentTask = getExistingParentTask(taskReq.getParentTask());
//		if(parentTask == null) {
//			parentTask = addParentTask(taskReq);
//		}
//		Query query = new Query();
//		query.addCriteria(Criteria.where("taskId").is(taskReq.getTaskId()));
//		Task task = mongoTemplate.findOne(query, Task.class); 		
//		
//		mongoTemplate.remove(query, Task.class);
//		taskReq.setTaskId(task.getTaskId());
//		taskReq.setParentId(parentTask.getParentId());
		Task task = new Task(taskReq.getParentId(),taskReq.getProjectId(), taskReq.getUserId(), taskReq.getTask(),taskReq.getStartDate(), taskReq.getEndDate(), taskReq.getPriority());
		
		mongoTemplate.save(task);
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

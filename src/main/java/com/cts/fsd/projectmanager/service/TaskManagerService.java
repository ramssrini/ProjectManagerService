package com.cts.fsd.projectmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.fsd.projectmanager.bean.ParentTask;
import com.cts.fsd.projectmanager.bean.Task;
import com.mongodb.client.result.DeleteResult;
@Service
public class TaskManagerService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public Task addTask(Task taskReq) {
		ParentTask parentTask = getExistingParentTask(taskReq.getParentTask());
		if(parentTask == null) {
			parentTask = addParentTask(taskReq);
		}
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "taskId"));
		query.limit(1);
		Task task = mongoTemplate.findOne(query, Task.class); 
		long id = task != null ? task.getTaskId():0;
		taskReq.setTaskId(id+1);
		taskReq.setParentId(parentTask.getParentId());
		// Adding Task data
		mongoTemplate.save(taskReq);
		
		return taskReq;
	}
	
	private ParentTask getExistingParentTask(String parentTaskDesc)
	{
		
		Query query = new Query();
		query.addCriteria(Criteria.where("parentTaskDesc").is(parentTaskDesc));
		ParentTask parentTask = mongoTemplate.findOne(query, ParentTask.class);
		
		return parentTask;
	}
	
	public ParentTask addParentTask(Task taskReq) {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "parentId"));
		query.limit(1);
		ParentTask parentTask = mongoTemplate.findOne(query, ParentTask.class); 
		ParentTask parentTaskNew = new ParentTask();
		parentTaskNew.setParentTaskDesc(taskReq.getParentTask());
		long id = parentTask != null? parentTask.getParentId() : 0;
		parentTaskNew.setParentId(id+1); 		
		mongoTemplate.save(parentTaskNew);
		return parentTaskNew;
	}
	
	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<>();
		tasks = mongoTemplate.findAll(Task.class);
		
		return tasks;
	}
	
	public Task getTasksById(String id) {
		Task task = new Task();
		Query query = new Query();
		query.addCriteria(Criteria.where("taskId").is(Long.valueOf(id)));
		task = mongoTemplate.findOne(query, Task.class);
		
		return task;
	}
	
	public Task updateTask(Task taskReq)
	{
		ParentTask parentTask = getExistingParentTask(taskReq.getParentTask());
		if(parentTask == null) {
			parentTask = addParentTask(taskReq);
		}
		Query query = new Query();
		query.addCriteria(Criteria.where("taskId").is(taskReq.getTaskId()));
		Task task = mongoTemplate.findOne(query, Task.class); 		
		
		mongoTemplate.remove(query, Task.class);
		taskReq.setTaskId(task.getTaskId());
		taskReq.setParentId(parentTask.getParentId());
		mongoTemplate.save(taskReq);
		return taskReq;
	}
	
	public long deleteTask(String  id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("taskId").is(Long.valueOf(id)));
		DeleteResult deleteResult =mongoTemplate.remove(query, Task.class);		
		return deleteResult.getDeletedCount();
	}
}

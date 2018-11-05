package com.cts.fsd.projectmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fsd.projectmanager.bean.Task;
import com.cts.fsd.projectmanager.service.TaskManagerService;


@RestController
public class TaskController {

@Autowired
TaskManagerService taskManagerService;

	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/getTasks", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTasks() {
    	
    	List<Task> tasks = taskManagerService.getAllTasks();
        return tasks;
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/getTask", produces=MediaType.APPLICATION_JSON_VALUE)
    public Task getTask( @RequestParam(value="id") String id) {
    	
    	Task task = taskManagerService.getTasksById(id);
    	System.out.println(task);
        return task;
    }

	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/addTask", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Task addTask(@RequestBody Task taskReq) {
    	

		Task task = taskManagerService.addTask(taskReq);
		
    	return task;
    }

	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/updateTask", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Task updateTask(@RequestBody Task taskReq) {
    	

		Task task = taskManagerService.updateTask(taskReq);
		
    	return task;
    }

	@CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value="/delete", produces=MediaType.APPLICATION_JSON_VALUE)
    public long delete( @RequestParam(value="id") String id) {
    	

		long deleteCount = taskManagerService.deleteTask(id);
		
    	return deleteCount;
    }
}

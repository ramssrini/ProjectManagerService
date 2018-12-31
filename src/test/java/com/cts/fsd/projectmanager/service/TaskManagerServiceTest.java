package com.cts.fsd.projectmanager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.cts.fsd.projectmanager.bean.ParentTask;
import com.cts.fsd.projectmanager.vo.Project;
import com.cts.fsd.projectmanager.vo.Task;
import com.mongodb.MongoClient;

public class TaskManagerServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddParentTask() {
		TaskManagerService managerService = new TaskManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		Task taskReq =  new Task("", "", "", "", "", 4, "", "Parent Task Test", "", "", "", "", "");
		managerService.addParentTask(taskReq );
		assertNotNull(taskReq.getParentId());
	}
	
	@Test
	public void testGetParentTasks() {
		TaskManagerService managerService = new TaskManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		List<ParentTask> list = managerService.getAllParentTasks();
		assertFalse(list.isEmpty());
	}

	@Test
	public void testAddTask() {
		TaskManagerService managerService = new TaskManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		Task taskReq =  new Task("12", "12", "Task Test", "11/11/2017", "11/11/2018", 4, "11", "Parent Task Test", "Test Last Name", "Test First Name", "123123", "1232", "1232");
		managerService.addTask(taskReq );
		assertNotNull(taskReq.getTaskId());
	}
	
	@Test
	public void testGetTasks() {
		TaskManagerService managerService = new TaskManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		List<Task> list = managerService.getAllTasks();
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void testUpdateTask() {
		TaskManagerService managerService = new TaskManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		Task taskReq =  new Task("12", "12", "Task Test Updated", "11/11/2017", "11/11/2018", 4, "11", "Parent Task Test", "Test Last Name", "Test First Name", "123123", "1232", "1232");
		taskReq.setTaskId("1");
		managerService.updateTask(taskReq );
		assertNotNull(taskReq.getTaskId());
	}
	
	@Test
	public void testDeleteTask() {
		TaskManagerService managerService = new TaskManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		managerService.deleteTask("1");
	}

}

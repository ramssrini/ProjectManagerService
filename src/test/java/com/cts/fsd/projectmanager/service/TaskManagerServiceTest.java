package com.cts.fsd.projectmanager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.cts.fsd.projectmanager.bean.ParentTask;
import com.cts.fsd.projectmanager.bean.User;
import com.cts.fsd.projectmanager.utils.Utils;
import com.cts.fsd.projectmanager.vo.Project;
import com.cts.fsd.projectmanager.vo.Task;
import com.mongodb.MongoClient;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
//	@Test
//	public void testAddTask() {
//		TaskManagerService managerService = new TaskManagerService();
//		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
//		managerService.utils = mock(Utils.class);
//		managerService.mongoTemplate = mongoTemplate;
//		managerService.mongoTemplate = new MongoTemplate(new MongoClient("192.168.99.100",27017), "taskmanagerdb");
//		Task taskReq =  new Task("12", "12", "Task Test", "11/11/2017", "11/11/2018", 4, "11", "Parent Task Test", "Test Last Name", "Test First Name", "123123", "1232", "1232");
//		when(managerService.utils.getNextSequence(isA(String.class))).thenReturn(new String("1"));
//		doNothing().when(mongoTemplate).save(taskReq);
//		doNothing().when(mongoTemplate).save(new User());
//		when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.User.class)).thenReturn(null); 
//		managerService.addTask(taskReq );
//		assertNotNull(taskReq.getTaskId());
//	}
	
	@Test
	public void testAddParentTask() {
		TaskManagerService managerService = new TaskManagerService();
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.utils = mock(Utils.class);
		managerService.mongoTemplate = mongoTemplate;
		Task taskReq =  new Task("", "", "", "", "", 4, "", "Parent Task Test", "", "", "", "", "");
		when(managerService.utils.getNextSequence(isA(String.class))).thenReturn(new String("1"));
		doNothing().when(mongoTemplate).save(taskReq);
		managerService.addParentTask(taskReq );
		assertNotNull(taskReq.getParentId());
	}
	
	@Test
	public void testGetParentTasks() {
		TaskManagerService managerService = new TaskManagerService();

		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;
		List<com.cts.fsd.projectmanager.bean.ParentTask> parentTasks = new ArrayList();
		com.cts.fsd.projectmanager.bean.ParentTask parentTask = new com.cts.fsd.projectmanager.bean.ParentTask();
		parentTask.set_id("11");
		parentTask.setParentTask("Test Parent Task");
		parentTasks.add(parentTask);

		when(mongoTemplate.findAll(com.cts.fsd.projectmanager.bean.ParentTask.class)).thenReturn(parentTasks); 
		
		
		List<ParentTask> list = managerService.getAllParentTasks();
		assertFalse(list.isEmpty());
	}

	
	@Test
	public void testGetTasks() {
		TaskManagerService managerService = new TaskManagerService();
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;
		List<com.cts.fsd.projectmanager.bean.Task> taskList = new ArrayList<>();
		com.cts.fsd.projectmanager.bean.Task task = new com.cts.fsd.projectmanager.bean.Task();
		task.setEndDate("11/11/2018");
		task.setPriority(10);
		task.setTask("Testing Task Name");
		task.setStartDate("11/12/2017");
		task.set_id("10");
		taskList.add(task);
		List<com.cts.fsd.projectmanager.bean.Project> projectList = new ArrayList<>();
		com.cts.fsd.projectmanager.bean.Project project = new com.cts.fsd.projectmanager.bean.Project();
		project.setEndDate("11/11/2018");
		project.setPriority(10);
		project.setProject("Testing Project");
		project.setStartDate("11/12/2017");
		project.set_id("10");
		projectList.add(project);

		List<com.cts.fsd.projectmanager.bean.User> users = new ArrayList<>();
		com.cts.fsd.projectmanager.bean.User user = new com.cts.fsd.projectmanager.bean.User();
		
		user.set_id("10");
		user.setEmployeeId("12454");
		user.setFirstName("Testing First Name");
		user.setLastName("Test Last Name");
		user.setProjectId("10");
		user.setTaskId("10");
		users.add(user);
		when(mongoTemplate.findAll(com.cts.fsd.projectmanager.bean.Task.class)).thenReturn(taskList); 
		when(mongoTemplate.findAll(com.cts.fsd.projectmanager.bean.User.class)).thenReturn(users); 
		when(mongoTemplate.findAll(com.cts.fsd.projectmanager.bean.Project.class)).thenReturn(projectList); 
		List<Task> list = managerService.getAllTasks();
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void testUpdateTask() {
		TaskManagerService managerService = new TaskManagerService();
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;
		Task taskReq =  new Task("12", "12", "Task Test Updated", "11/11/2017", "11/11/2018", 4, "11", "Parent Task Test", "Test Last Name", "Test First Name", "123123", "1232", "1232");
		taskReq.setTaskId("1");
		doNothing().when(mongoTemplate).save(new com.cts.fsd.projectmanager.bean.Project()); 
		doNothing().when(mongoTemplate).save(new com.cts.fsd.projectmanager.bean.User()); 
		doNothing().when(mongoTemplate).save(new com.cts.fsd.projectmanager.bean.Task()); 
		when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.User.class)).thenReturn(null); 
		when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.Project.class)).thenReturn(null);
		when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.Task.class)).thenReturn(null);
		 
		managerService.updateTask(taskReq );
		assertNotNull(taskReq.getTaskId());
	}
	
	@Test
	public void testDeleteTask() {
		TaskManagerService managerService = new TaskManagerService();
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;
		when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.Task.class)).thenReturn(null);
		assertEquals(0L, managerService.deleteTask("0"));
	}

}

package com.cts.fsd.projectmanager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.cts.fsd.projectmanager.utils.Utils;
import com.cts.fsd.projectmanager.vo.Project;
import com.mongodb.client.result.DeleteResult;

public class ProjectManagerServiceTest {

	
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
	public void testAddProject() {
		ProjectManagerService managerService = new ProjectManagerService();
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.utils = mock(Utils.class);
		managerService.mongoTemplate = mongoTemplate;
		Project projectReq = new Project();
		projectReq.set_id("1111");
		projectReq.setEmployeeId("148975");
		projectReq.setEndDate("11/11/2019");
		projectReq.setFirstName("Rameskumar");
		projectReq.setLastName("Srinivasan");
		projectReq.setPriority(10);
		projectReq.setProject("Project Junit 1");
		projectReq.setStartDate("11/11/2018");
		projectReq.setTaskId("12");
		projectReq.setUserid("22");
		when(managerService.utils.getNextSequence(isA(String.class))).thenReturn(new String("1"));
		doNothing().when(mongoTemplate).save(projectReq);
		managerService.addProject(projectReq );
		assertNotNull(projectReq.get_id());
	}
	
	@Test
	public void testGetProject() {
		ProjectManagerService managerService = new ProjectManagerService();
//		managerService.mongoTemplate = new MongoTemplate(new MongoClient("192.168.99.100",27017), "taskmanagerdb");
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;
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
		when(mongoTemplate.findAll(com.cts.fsd.projectmanager.bean.User.class)).thenReturn(users); 
		when(mongoTemplate.findAll(com.cts.fsd.projectmanager.bean.Project.class)).thenReturn(projectList); 
		List<Project> projects = managerService.getAllProjects();
		assertFalse(projects.isEmpty());
	}
	
	@Test
	public void testUpdateProject() {
		ProjectManagerService managerService = new ProjectManagerService();
//		managerService.mongoTemplate = new MongoTemplate(new MongoClient("192.168.99.100",27017), "taskmanagerdb");
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;
		Project projectReq = new Project();
		projectReq.set_id("1");
		projectReq.setEmployeeId("148975");
		projectReq.setEndDate("11/11/2019");
		projectReq.setFirstName("Rameskumar");
		projectReq.setLastName("Srinivasan");
		projectReq.setPriority(10);
		projectReq.setProject("Project Junit 1 Updated");
		projectReq.setStartDate("11/11/2018");
		projectReq.setTaskId("12");
		projectReq.setUserid("22");

		 doNothing().when(mongoTemplate).save(new com.cts.fsd.projectmanager.bean.Project()); 
		 doNothing().when(mongoTemplate).save(new com.cts.fsd.projectmanager.bean.User()); 
		 when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.User.class)).thenReturn(null); 
		 when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.Project.class)).thenReturn(null);
		Project project = managerService.updateProject(projectReq);
		assertEquals(projectReq.getProject(), project.getProject());
	}
	
	@Test
	public void testRemoveProject() {
		ProjectManagerService managerService = new ProjectManagerService();
//		managerService.mongoTemplate = new MongoTemplate(new MongoClient("192.168.99.100",27017), "taskmanagerdb");
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;
		
		Project projectReq = new Project();
		projectReq.set_id("1");
		projectReq.setEmployeeId("148975");
		projectReq.setEndDate("11/11/2019");
		projectReq.setFirstName("Rameskumar");
		projectReq.setLastName("Srinivasan");
		projectReq.setPriority(10);
		projectReq.setProject("Project Junit 1 Updated");
		projectReq.setStartDate("11/11/2018");
		projectReq.setTaskId("12");
		projectReq.setUserid("22");
		DeleteResult deleteResult =new DeleteResult() {
			
			@Override
			public boolean wasAcknowledged() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public long getDeletedCount() {
				// TODO Auto-generated method stub
				return 1;
			}
		};
		when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.Project.class)).thenReturn(deleteResult);
		assertEquals(0L, managerService.deleteProject(projectReq));
	}


}

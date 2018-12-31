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

import com.cts.fsd.projectmanager.vo.Project;
import com.mongodb.MongoClient;

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
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		Project projectReq = new Project();
//		projectReq.set_id("1111");
		projectReq.setEmployeeId("148975");
		projectReq.setEndDate("11/11/2019");
		projectReq.setFirstName("Rameskumar");
		projectReq.setLastName("Srinivasan");
		projectReq.setPriority(10);
		projectReq.setProject("Project Junit 1");
		projectReq.setStartDate("11/11/2018");
		projectReq.setTaskId("12");
		projectReq.setUserid("22");
		managerService.addProject(projectReq );
		assertNotNull(projectReq.get_id());
	}
	
	@Test
	public void testGetProject() {
		ProjectManagerService managerService = new ProjectManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		List<Project> projects = managerService.getAllProjects();
		assertFalse(projects.isEmpty());
	}
	
	@Test
	public void testUpdateProject() {
		ProjectManagerService managerService = new ProjectManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
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
		Project project = managerService.updateProject(projectReq);
		assertEquals(projectReq.getProject(), project.getProject());
	}
	
	@Test
	public void testRemoveProject() {
		ProjectManagerService managerService = new ProjectManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
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
		long project = managerService.deleteProject(projectReq);
		assertEquals(1, project);
	}


}

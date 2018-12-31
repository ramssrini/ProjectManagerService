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

import com.cts.fsd.projectmanager.bean.User;
import com.cts.fsd.projectmanager.vo.Project;
import com.mongodb.MongoClient;

public class UserManagerServiceTest {


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
	public void testAddUser() {
		UserManagerService managerService = new UserManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		
		User userReq = new User();
		userReq.setEmployeeId("148989");
		userReq.setFirstName("Test First Name");
		userReq.setLastName("Test Last Name");
		managerService.addUser(userReq );
		
		assertNotNull(userReq.get_id());
	}
	
	@Test
	public void testGetUser() {
		UserManagerService managerService = new UserManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		List<User> users = managerService.getAllUsers();
		assertFalse(users.isEmpty());
	}
	
	@Test
	public void testUpdateUser() {
		UserManagerService managerService = new UserManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		User userReq = new User();
		userReq.setEmployeeId("148989");
		userReq.setFirstName("Test First Name");
		userReq.setLastName("Test Last Name");
		User user = managerService.updateUser(userReq);
		assertEquals(userReq.getLastName(), user.getLastName());
	}
	
	@Test
	public void testRemoveUser() {
		UserManagerService managerService = new UserManagerService();
		managerService.mongoTemplate = new MongoTemplate(new MongoClient("localhost",27017), "taskmanagerdb");
		
		long project = managerService.deleteUser("1");
	}


}

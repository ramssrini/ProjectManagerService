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

import com.cts.fsd.projectmanager.bean.User;
import com.cts.fsd.projectmanager.utils.Utils;
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
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.utils = mock(Utils.class);
		managerService.mongoTemplate = mongoTemplate;
		
		User userReq = new User();
		userReq.setEmployeeId("148989");
		userReq.setFirstName("Test First Name");
		userReq.setLastName("Test Last Name");
		when(managerService.utils.getNextSequence(isA(String.class))).thenReturn(new String("1"));
		doNothing().when(mongoTemplate).save(userReq);
		managerService.addUser(userReq );
		
		assertNotNull(userReq.get_id());
	}
	
	@Test
	public void testGetUser() {
		UserManagerService managerService = new UserManagerService();
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;

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
		List<User> usersRes = managerService.getAllUsers();
		assertFalse(usersRes.isEmpty());
	}
	
	@Test
	public void testUpdateUser() {
		UserManagerService managerService = new UserManagerService();
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;
		User userReq = new User();
		userReq.setEmployeeId("148989");
		userReq.setFirstName("Test First Name");
		userReq.setLastName("Test Last Name");
		doNothing().when(mongoTemplate).save(new com.cts.fsd.projectmanager.bean.User()); 
		when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.User.class)).thenReturn(null); 
		User user = managerService.updateUser(userReq);
		assertEquals(userReq.getLastName(), user.getLastName());
	}
	
	@Test
	public void testRemoveUser() {
		UserManagerService managerService = new UserManagerService();
		MongoTemplate  mongoTemplate = mock(MongoTemplate.class);
		managerService.mongoTemplate = mongoTemplate;
		when(mongoTemplate.remove(new Query(), com.cts.fsd.projectmanager.bean.Project.class)).thenReturn(null);
		assertEquals(0L, managerService.deleteUser("0"));
	}


}

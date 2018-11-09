package com.cts.fsd.projectmanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.fsd.projectmanager.bean.Task;
import com.cts.fsd.projectmanager.bean.User;
import com.cts.fsd.projectmanager.service.UserManagerService;
import com.google.gson.Gson;
@RunWith(SpringRunner.class)
@WebMvcTest(UserControllerTest.class)
public class UserControllerTest {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private UserManagerService userManagerService;

	@Autowired
	private MockMvc mockMvc;
	@Test
	public void addUser() {
		User user = new User();
		user.setEmployeeId("12323131");
		user.setFirstName("Test First Name");
		user.setLastName("Test Last Name");
		user.setProjectId(12);
		user.setTaskId(23);
		Gson gson = new Gson();
		String json = gson.toJson(user);
		
		when(this.userManagerService.addUser(user)).thenReturn(user);
		try {
			this.mockMvc
					.perform(post("/addUser").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getTasks() {
		User user = new User();
		user.setEmployeeId("12323131");
		user.setFirstName("Test First Name");
		user.setLastName("Test Last Name");
		user.setProjectId(12);
		user.setTaskId(23);
		Gson gson = new Gson();
		String json = gson.toJson(user);
		when(this.userManagerService.getUserById("1")).thenReturn(user);
		try {
			this.mockMvc
					.perform(get("/getUsers").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content("1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTask() {
		User user = new User();
		user.setEmployeeId("12323131");
		user.setFirstName("Updated First Name");
		user.setLastName("Updated Last Name");
		user.setProjectId(12);
		user.setTaskId(23);
		user.setUserId(1);
		Gson gson = new Gson();
		String json = gson.toJson(user);
		when(this.userManagerService.getUserById("1")).thenReturn(user);
		try {
			this.mockMvc
					.perform(post("/updateUser").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

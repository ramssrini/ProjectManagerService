package com.cts.fsd.projectmanager.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.cts.fsd.projectmanager.service.TaskManagerService;
import com.google.gson.Gson;
@RunWith(SpringRunner.class)
@WebMvcTest(TaskControllerTest.class)
public class TaskControllerTest {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private TaskManagerService taskManagerService;

	@Autowired
	private MockMvc mockMvc;
	@Test
	public void addTask() {
		Task task = new Task();
		task.setEndDate("11/11/2018");
		task.setStartDate("11/11/2017");
		task.setParentTask("Test Parent Task");
		task.setTask("Test Task Description");
		task.setPriority(3);
		Gson gson = new Gson();
		String json = gson.toJson(task);
		when(this.taskManagerService.addTask(task)).thenReturn(task);
		try {
			this.mockMvc
					.perform(post("/addTask").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getTasks() {
		Task task = new Task();
		task.setEndDate("11/11/2018");
		task.setStartDate("11/11/2017");
		task.setParentTask("Test Parent Task");
		task.setTask("Test Task Description");
		task.setPriority(3);
		Gson gson = new Gson();
		String json = gson.toJson(task);
		when(this.taskManagerService.getTasksById("1")).thenReturn(task);
		try {
			this.mockMvc
					.perform(get("/getTasks").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content("1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTask() {
		Task task = new Task();
		task.setEndDate("11/10/2018");
		task.setStartDate("11/10/2017");
		task.setParentTask("Test Parent Task");
		task.setTask("Test Task Description");
		task.setPriority(5);
		Gson gson = new Gson();
		String json = gson.toJson(task);
		when(this.taskManagerService.getTasksById("1")).thenReturn(task);
		try {
			this.mockMvc
					.perform(post("/updateTask").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

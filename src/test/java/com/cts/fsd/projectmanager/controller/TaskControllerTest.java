package com.cts.fsd.projectmanager.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

import com.cts.fsd.projectmanager.bean.ParentTask;
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
		com.cts.fsd.projectmanager.vo.Task task = new com.cts.fsd.projectmanager.vo.Task();
		task.setEndDate("11/11/2018");
		task.setStartDate("11/11/2017");
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
	public void addParentTask() {
		com.cts.fsd.projectmanager.vo.Task task = new com.cts.fsd.projectmanager.vo.Task();

		task.setParentTask("Test Parent Task Description");
		Gson gson = new Gson();
		String json = gson.toJson(task);
		
		when(this.taskManagerService.addParentTask(task)).thenReturn(task);
		try {
			this.mockMvc
					.perform(post("/addParentTask").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getTasks() {
		com.cts.fsd.projectmanager.vo.Task task = new com.cts.fsd.projectmanager.vo.Task();
		task.setEndDate("11/11/2018");
		task.setStartDate("11/11/2017");
		task.setTask("Test Task Description");
		task.setPriority(3);
		Gson gson = new Gson();
		String json = gson.toJson(task);
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
	public void getTaskById() {
		com.cts.fsd.projectmanager.vo.Task task = new com.cts.fsd.projectmanager.vo.Task();
		task.setEndDate("11/11/2018");
		task.setStartDate("11/11/2017");
		task.setTask("Test Task Description");
		task.setPriority(3);
		Gson gson = new Gson();
		String json = gson.toJson(task);
		when(this.taskManagerService.getTasksById("1")).thenReturn(task);
		try {
			this.mockMvc
					.perform(get("/getTask?id=1").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content("1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getParentTasks() {
		
		List<ParentTask> parentTasks = new ArrayList<>();
		ParentTask parentTask1 = new ParentTask();
		parentTask1.set_id("1");
		parentTask1.setParentTask("Parent Test task 1");
		ParentTask parentTask2 = new ParentTask();
		parentTask2.set_id("2");
		parentTask2.setParentTask("Parent Test task 2");
		parentTasks.add(parentTask1);
		parentTasks.add(parentTask2);
		when(this.taskManagerService.getAllParentTasks()).thenReturn(parentTasks);
		try {
			this.mockMvc
					.perform(get("/getParentTasks").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content("1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTask() {
		com.cts.fsd.projectmanager.vo.Task task = new com.cts.fsd.projectmanager.vo.Task();
		task.setEndDate("11/10/2018");
		task.setStartDate("11/10/2017");
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
	

	@Test
	public void deleteTask() {
		com.cts.fsd.projectmanager.vo.Task task = new com.cts.fsd.projectmanager.vo.Task();
		task.setEndDate("11/10/2018");
		task.setStartDate("11/10/2017");
		task.setTask("Test Task Description");
		task.setPriority(5);
		Gson gson = new Gson();
		String json = gson.toJson(task);
//		when(this.taskManagerService.getTasksById("1")).thenReturn(task);
		try {
			this.mockMvc
					.perform(delete("/delete").accept(MediaType.ALL)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.ALL).param("id", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

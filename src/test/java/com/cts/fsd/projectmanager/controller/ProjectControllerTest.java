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

import com.cts.fsd.projectmanager.bean.Project;
import com.cts.fsd.projectmanager.bean.Task;
import com.cts.fsd.projectmanager.bean.User;
import com.cts.fsd.projectmanager.service.ProjectManagerService;
import com.cts.fsd.projectmanager.service.UserManagerService;
import com.google.gson.Gson;
@RunWith(SpringRunner.class)
@WebMvcTest(ProjectControllerTest.class)
public class ProjectControllerTest {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private ProjectManagerService projectManagerService;

	@Autowired
	private MockMvc mockMvc;
	@Test
	public void addUser() {
		Project project = new Project();
		project.setProject("Test Project Name");
		project.setPriority(4);
		project.setStartDate("12/12/2017");
		project.setEndDate("12/12/2018");
		Gson gson = new Gson();
		String json = gson.toJson(project);
		
		when(this.projectManagerService.addProject(project)).thenReturn(project);
		try {
			this.mockMvc
					.perform(post("/addProject").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getTasks() {
		Project project = new Project();
		project.setProject("Test Project Name");
		project.setPriority(4);
		project.setStartDate("12/12/2017");
		project.setEndDate("12/12/2018");
		Gson gson = new Gson();
		String json = gson.toJson(project);
		when(this.projectManagerService.getProjectById("1")).thenReturn(project);
		try {
			this.mockMvc
					.perform(get("/getProjects").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content("1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTask() {
		Project project = new Project();
		project.setProject("Test Project Name Update");
		project.setPriority(4);
		project.setStartDate("12/12/2017");
		project.setEndDate("12/12/2018");
		Gson gson = new Gson();
		String json = gson.toJson(project);
		when(this.projectManagerService.getProjectById("1")).thenReturn(project);
		try {
			this.mockMvc
					.perform(post("/updateProject").accept(MediaType.APPLICATION_JSON)
							.characterEncoding(StandardCharsets.UTF_8.toString())
							.contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

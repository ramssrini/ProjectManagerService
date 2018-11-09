package com.cts.fsd.projectmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fsd.projectmanager.bean.Project;
import com.cts.fsd.projectmanager.service.ProjectManagerService;


@RestController
public class ProjectController {	
	@Autowired
	ProjectManagerService projectManagerService;
	
		@CrossOrigin(origins = "http://localhost:4200")
	    @GetMapping(value="/getProjects", produces=MediaType.APPLICATION_JSON_VALUE)
	    public List<Project> getProjects() {
	    	
	    	List<Project> projects = projectManagerService.getAllProjects();
	        return projects;
	    }
		
		@CrossOrigin(origins = "http://localhost:4200")
	    @GetMapping(value="/getProject", produces=MediaType.APPLICATION_JSON_VALUE)
	    public Project getProject( @RequestParam(value="id") String id) {
	    	
			Project project = projectManagerService.getProjectById(id);
	    	System.out.println(project);
	        return project;
	    }
	
		@CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping(value="/addProject", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public Project addProject(@RequestBody Project projectReq) {
	    	
	
			Project project = projectManagerService.addProject(projectReq);
			
	    	return project;
	    }
	
		@CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping(value="/updateProject", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public Project updateProject(@RequestBody Project projectReq) {
	    	
	
			Project project = projectManagerService.updateProject(projectReq);
			
	    	return project;
	    }
	
		@CrossOrigin(origins = "http://localhost:4200")
	    @DeleteMapping(value="/deleteProject", produces=MediaType.APPLICATION_JSON_VALUE)
	    public long deleteProject( @RequestParam(value="id") String id) {
	    	
	
			long deleteCount = projectManagerService.deleteProject(id);
			
	    	return deleteCount;
	    }
	
}

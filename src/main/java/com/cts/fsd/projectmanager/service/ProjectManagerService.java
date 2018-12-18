package com.cts.fsd.projectmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cts.fsd.projectmanager.bean.Project;
import com.cts.fsd.projectmanager.bean.Task;
import com.cts.fsd.projectmanager.bean.User;
import com.cts.fsd.projectmanager.utils.Utils;
import com.mongodb.client.result.DeleteResult;
@Service
public class ProjectManagerService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public Project addProject(Project projectReq) {
		
//		Query query = new Query();
//		query.with(new Sort(Sort.Direction.DESC, "userId"));
//		query.limit(1);
//		Project project = mongoTemplate.findOne(query, Project.class); 
//		long id = project != null ? project.getProjectId():0;
//		projectReq.setProjectId(id+1);
		// Adding User data
		projectReq.set_id(Utils.getNextSequence("projectid").toString());
		mongoTemplate.save(projectReq);
		
		return projectReq;
	}
	
	
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		projects = mongoTemplate.findAll(Project.class);
		
		return projects;
	}
	
	public Project getProjectById(String id) {
		Project project = new Project();
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(Long.valueOf(id)));
		project = mongoTemplate.findOne(query, Project.class);
		
		return project;
	}
	
	public Project updateProject(Project projectReq)
	{
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(projectReq.get_id()));
//		Project project = mongoTemplate.findOne(query, Project.class); 	
		mongoTemplate.remove(query, User.class);
		mongoTemplate.save(projectReq);
		return projectReq;
	}
	
	public long deleteProject(String  id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		DeleteResult deleteResult =mongoTemplate.remove(query, Project.class);		
		return deleteResult.getDeletedCount();
	}
}

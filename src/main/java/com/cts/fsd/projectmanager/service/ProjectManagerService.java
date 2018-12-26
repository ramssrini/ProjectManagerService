package com.cts.fsd.projectmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	public com.cts.fsd.projectmanager.vo.Project addProject(com.cts.fsd.projectmanager.vo.Project projectReq) {
	

		projectReq.set_id(Utils.getNextSequence("projectid").toString());
		Project project = new Project();
		project.setEndDate(projectReq.getEndDate());
		project.setStartDate(projectReq.getStartDate());
		project.setPriority(projectReq.getPriority());
		project.setProject(projectReq.getProject());
		project.setEndDate(projectReq.getEndDate());
		project.set_id(projectReq.get_id());
		mongoTemplate.save(project);
		
		User user = new User();
		user.setEmployeeId(projectReq.getEmployeeId());
		user.setLastName(projectReq.getLastName());
		user.setFirstName(projectReq.getFirstName());
		user.setProjectId(projectReq.get_id());
		user.setTaskId(projectReq.getTaskId());
		user.set_id(projectReq.getUserid());
		Query queryUser = new Query();
		queryUser.addCriteria(Criteria.where("_id").is(projectReq.getUserid()));
		mongoTemplate.remove(queryUser, User.class);
		mongoTemplate.save(user);
		
		
		
		return projectReq;
	}
	
	
	public List<com.cts.fsd.projectmanager.vo.Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		projects = mongoTemplate.findAll(Project.class);
		List<com.cts.fsd.projectmanager.vo.Project> projectList = new ArrayList<>();
		
		Map<String,Project> map = new HashMap<>();
		
		for(Project proj : projects)
		{
			map.put(proj.get_id(), proj);
		}
		
		
		
		List<User> userList  = mongoTemplate.findAll(User.class);
		Map<String, User> userMap = new HashMap<>();
		
		for(User user : userList) {
			userMap.put(user.getProjectId(), user);
		}
		
		for(Project proj : projects)
		{
			if(proj != null)
			{
				com.cts.fsd.projectmanager.vo.Project project = new com.cts.fsd.projectmanager.vo.Project();
				project.setProject(proj.getProject());
				project.setPriority(proj.getPriority());
				project.setEndDate(proj.getEndDate());
				project.setStartDate(proj.getStartDate());
				project.set_id(proj.get_id());
				User user = userMap.get(proj.get_id());
				
				if(user != null)
				{

					project.setEmployeeId(user.getEmployeeId());
					project.setUserid(user.get_id());
					project.setFirstName(user.getFirstName());
					project.setLastName(user.getLastName());
					project.setTaskId(user.getTaskId());
				}
				
				projectList.add(project);
			
			}
		}
		
		
		
		return projectList;
	}
	
//	public Project getProjectById(String id) {
//		Project project = new Project();
//		Query query = new Query();
//		query.addCriteria(Criteria.where("_id").is(Long.valueOf(id)));
//		project = mongoTemplate.findOne(query, Project.class);
//		
//		return project;
//	}
	
	public com.cts.fsd.projectmanager.vo.Project updateProject(com.cts.fsd.projectmanager.vo.Project projectReq)
	{		
		
		
		Project project = new Project();
		project.setEndDate(projectReq.getEndDate());
		project.setStartDate(projectReq.getStartDate());
		project.setPriority(projectReq.getPriority());
		project.setProject(projectReq.getProject());
		project.setEndDate(projectReq.getEndDate());
		project.set_id(projectReq.get_id());
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(projectReq.get_id()));
		mongoTemplate.remove(query, Project.class);
		mongoTemplate.save(project);
		
		User user = new User();
		user.setEmployeeId(projectReq.getEmployeeId());
		user.setLastName(projectReq.getLastName());
		user.setFirstName(projectReq.getFirstName());
		user.setProjectId(projectReq.get_id());
		user.setTaskId(projectReq.getTaskId());
		user.set_id(projectReq.getUserid());
		Query queryUser = new Query();
		queryUser.addCriteria(Criteria.where("_id").is(projectReq.getUserid()));
		mongoTemplate.remove(queryUser, User.class);
		mongoTemplate.save(user);
		
		
		return projectReq;
	}
	
	public long deleteProject(com.cts.fsd.projectmanager.vo.Project projectReq)
	{
		User user = new User();
		user.setEmployeeId(projectReq.getEmployeeId());
		user.setLastName(projectReq.getLastName());
		user.setFirstName(projectReq.getFirstName());
		user.setProjectId("");
		user.setTaskId(projectReq.getTaskId());
		user.set_id(projectReq.getUserid());
		Query queryUser = new Query();
		queryUser.addCriteria(Criteria.where("_id").is(projectReq.getUserid()));
		mongoTemplate.remove(queryUser, User.class);
		mongoTemplate.save(user);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(projectReq.get_id()));
		DeleteResult deleteResult =mongoTemplate.remove(query, Project.class);		
		return deleteResult.getDeletedCount();
	}
}

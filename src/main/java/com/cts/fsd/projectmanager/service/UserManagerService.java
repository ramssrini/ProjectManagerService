package com.cts.fsd.projectmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cts.fsd.projectmanager.bean.Task;
import com.cts.fsd.projectmanager.bean.User;
import com.mongodb.client.result.DeleteResult;
@Service
public class UserManagerService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public User addUser(User userReq) {
		
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "userId"));
		query.limit(1);
		User user = mongoTemplate.findOne(query, User.class); 
		long id = user != null ? user.getTaskId():0;
		userReq.setUserId(id+1);
		// Adding User data
		mongoTemplate.save(userReq);
		
		return userReq;
	}
	
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		users = mongoTemplate.findAll(User.class);
		
		return users;
	}
	
	public User getUserById(String id) {
		User user = new User();
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(Long.valueOf(id)));
		user = mongoTemplate.findOne(query, User.class);
		
		return user;
	}
	
	public User updateUser(User userReq)
	{
		
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userReq.getUserId()));
		User user = mongoTemplate.findOne(query, User.class); 	
		mongoTemplate.remove(query, User.class);
		mongoTemplate.save(userReq);
		return userReq;
	}
	
	public long deleteUser(String  id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(Long.valueOf(id)));
		DeleteResult deleteResult =mongoTemplate.remove(query, User.class);		
		return deleteResult.getDeletedCount();
	}
}

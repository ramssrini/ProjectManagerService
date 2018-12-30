package com.cts.fsd.projectmanager.utils;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Utils {

	
	public static Object getNextSequence(String name){
	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	    // Now connect to your databases
	    DB db = mongoClient.getDB("taskmanagerdb");
	    DBCollection collection = db.getCollection("counters");
	    System.out.println("collection.count()>>>>>>>"+collection.count());
	    if(collection.count() == 0)
	    {
	    	Map mapProject = new HashMap<>();
	    	mapProject.put("_id", "projectid");
	    	mapProject.put("seq", 0);
	    	DBObject dbObject = new BasicDBObject();
	    	collection = db.createCollection("counters",dbObject);
	    	collection.insert(new BasicDBObject(mapProject));
	    	Map mapUser = new HashMap<>();
	    	mapUser.put("_id", "userid");
	    	mapUser.put("seq", 0);
	    	collection.insert(new BasicDBObject(mapUser));
	    	
	    	Map mapTask = new HashMap<>();
	    	mapTask.put("_id", "taskid");
	    	mapTask.put("seq", 0);
	    	collection.insert(new BasicDBObject(mapTask));
	    	
	    	Map mapParentTask = new HashMap<>();
	    	mapParentTask.put("_id", "parentTaskid");
	    	mapParentTask.put("seq", 0);
	    	collection.insert(new BasicDBObject(mapParentTask));
	    }
	    BasicDBObject find = new BasicDBObject();
	    find.put("_id", name);
	    BasicDBObject update = new BasicDBObject();
	    update.put("$inc", new BasicDBObject("seq", 1));
	    DBObject obj =  collection.findAndModify(find, update);
	    return obj.get("seq");
	}
}

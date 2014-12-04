package edu.sjsu.cmpe295.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class UserDao {
	MongoClient mongoClient;
	DB db;
	DBCursor cursor;
	DBCollection recommendCol;
	public UserDao(){
		try{
			mongoClient = new MongoClient( "192.168.17.187" , 27017 );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		db = mongoClient.getDB("processedData");
		recommendCol = db.getCollection("recData");
	}
	//finding all the users
	public List<String> findAllUsers(String userId){
		List<String> recList = new ArrayList<String>();
		cursor = recommendCol.find();
		try {
		   while(cursor.hasNext()) {
		       recList.add(cursor.next().toString());
		   }
		} finally {
		   cursor.close();
		}
		return recList;
	}
	
	//finding recos of a user by ID
	public String findUserById(String userId){
		BasicDBObject query = new BasicDBObject("user_id", userId);
		cursor = recommendCol.find(query);
		try {
		   while(cursor.hasNext()) {
		       return cursor.next().toString();
		   }
		} finally {
		   cursor.close();
		}
		return null;
	}
	
	//(dummy data method) Recommend users for particular businessID
	public List<String> recommendUsers(String businessId){
		List<String> users = new ArrayList<String>();
		BasicDBObject orQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("recommendation1",businessId));
		obj.add(new BasicDBObject("recommendation2",businessId));
		obj.add(new BasicDBObject("recommendation3",businessId));
		orQuery.put("$or", obj);
		cursor = recommendCol.find(orQuery);
		while (cursor.hasNext()) {
			users.add(cursor.next().toString());
		}
		
		System.out.println(users);
		return users;
	}
	
	//Recommend users for particular businessID
	public List<String> recUsers(String businessId){
		
		List<String> users = new ArrayList<String>();
        //Query to search a value in recommendation fields
		BasicDBObject orQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("recommendation1",businessId));
		obj.add(new BasicDBObject("recommendation2",businessId));
		obj.add(new BasicDBObject("recommendation3",businessId));
		orQuery.put("$or", obj);
	 
		//Query to make limited field
		BasicDBObject xQuery = new BasicDBObject();
		List<BasicDBObject> to = new ArrayList<BasicDBObject>();
		to.add(new BasicDBObject("user_name",1));
		to.add(new BasicDBObject("user_id",1));
		xQuery.append("user_name", 1).append("user_id", 1);
		//DBCollection recCol = db.getCollection("recData");
		cursor = recommendCol.find(orQuery,xQuery);
		cursor.limit(3);
		System.out.println(cursor.size());
		while (cursor.hasNext()) {
			users.add(cursor.next().toString());
		}
		
		return users;

	}
	
}

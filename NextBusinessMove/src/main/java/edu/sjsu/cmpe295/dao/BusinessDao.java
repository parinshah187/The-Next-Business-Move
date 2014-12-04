package edu.sjsu.cmpe295.dao;

import edu.sjsu.cmpe295.domain.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class BusinessDao {

	MongoClient mongoClient;
	DB db;
	DBCursor cursor;
	DBObject analyzedObj;
	DBObject bizObj;
	DBCollection bizData;
	DBCollection analyzedBiz;
	public BusinessDao(){
		try {
			mongoClient = new MongoClient( "192.168.17.187" , 27017 );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        db = mongoClient.getDB("processedData");
    	analyzedBiz = db.getCollection("analyzedBiz");
        bizData = db.getCollection("businessData");
	}

	//finding one business document using ID
	public String findBusinessById(String id){

		//BasicDBObject query = new BasicDBObject("business_id", id);

		DBObject query = new BasicDBObject("business_id", id);
		analyzedObj = analyzedBiz.findOne(query);
			// check
			bizObj = bizData.findOne(query);
			Map<String,Object> hm = new HashMap<String,Object>();
			hm.put("attributes", bizObj.get("attributes"));
			analyzedObj.putAll(hm);
			//System.out.println(obj);
			return analyzedObj.toString();

		/*		cursor = analyzedBiz.find(query);
		try {
		   while(cursor.hasNext()) {
		       return cursor.next().toString();
		   }
		} finally {
		   cursor.close();
		}
		return null;
		*/
	}
	
	//finding all the business documents
	public List<String> findAllBusiness(){
		List<String> businessList = new ArrayList<String>();
		cursor = bizData.find();
		try {
		   while(cursor.hasNext()) {
		       businessList.add(cursor.next().toString());
		   }
		} finally {
		   cursor.close();
		}
		return businessList;
	}
	
	
/*	//Recommend users for particular businessID
	public List<String> recommendUsers(String businessId){
		List<String> users = new ArrayList<String>();
		BasicDBObject orQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("recommendation1",businessId));
		obj.add(new BasicDBObject("recommendation2",businessId));
		obj.add(new BasicDBObject("recommendation3",businessId));
		orQuery.put("$or", obj);
		cursor = analyzedCol.find(orQuery);
		while (cursor.hasNext()) {
			users.add(cursor.next().toString());
		}
		
		System.out.println(users);
		return users;
	}*/
	//finding business documents of particular category
	
	//finding business documents which have the word from search functionality
	
}

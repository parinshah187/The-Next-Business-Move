package edu.sjsu.cmpe295.dao;

import edu.sjsu.cmpe295.domain.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class BusinessDao {

	MongoClient mongoClient;
	DB db;
	DBCursor cursor;
	DBCollection analyzedCol;
	public BusinessDao(){
		try {
			mongoClient = new MongoClient( "192.168.17.132" , 27017 );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        db = mongoClient.getDB("processedData");
    	analyzedCol = db.getCollection("analyzedBiz");
	}

	//finding one business document using ID
	public String findBusinessById(String id){

		BasicDBObject query = new BasicDBObject("business_id", id);
		cursor = analyzedCol.find(query);
		try {
		   while(cursor.hasNext()) {
		       return cursor.next().toString();
		   }
		} finally {
		   cursor.close();
		}
		return null;
	}
	
	//finding all the business documents
	public List<String> findAllBusiness(){
		List<String> businessList = new ArrayList<String>();
		cursor = analyzedCol.find();
		try {
		   while(cursor.hasNext()) {
		       businessList.add(cursor.next().toString());
		   }
		} finally {
		   cursor.close();
		}
		return businessList;
	}
	
	//finding business documents of particular category
	
	//finding business documents which have the word from search functionality
	
}

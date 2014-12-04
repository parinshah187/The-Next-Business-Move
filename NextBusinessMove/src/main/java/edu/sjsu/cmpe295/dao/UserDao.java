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
			mongoClient = new MongoClient( "192.168.17.132" , 27017 );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		db = mongoClient.getDB("processedData");
		
		recommendCol = db.getCollection("recommendedData");
	}
	//finding user recommendations
	public List<String> findRecosById(String userId){
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
	
}

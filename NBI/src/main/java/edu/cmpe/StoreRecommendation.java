package edu.cmpe;

import java.math.BigInteger;
import java.net.UnknownHostException;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class StoreRecommendation {

	private static MongoClient mongoClient;
	private static DB db;
	private static DBCollection collection;

	public StoreRecommendation() {
		// TODO Auto-generated constructor stub
		try {
			mongoClient = new MongoClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception thrown in creating mongoClient");
		}
		db = mongoClient.getDB("nextbusinessmove");
		collection = db.getCollection("recommendationdata");
	}

	public void store(JSONArray jsonArray) {
		
		for(int i=0;i<jsonArray.length();i++)
		{
			
		JSONObject jsonObject= (JSONObject) jsonArray.get(i);
		DBObject dbo = (DBObject) JSON.parse(jsonObject.toString());
		
		// System.out.println("Encoding json object to dbobject...");
		DBObject dboObject = encode(jsonObject);
		// System.out.println("Encoded json object to dbobject...");
		// System.out.println("Storing json object in mongodb...");
		System.out.println(dboObject.get("user_id"));
		collection.insert(dbo);
		// System.out.println("Stored json object in Mongodb");
		// System.out.println("\n");
		}
	}

	public static DBObject encode(JSONObject jsonObject) {
		BasicDBObject result = new BasicDBObject();
		Set<String> keySet = jsonObject.keySet();
		for (String s : keySet) {
			Object v = jsonObject.get(s);
			if (v instanceof JSONObject) {
				result.put(s, encode((JSONObject) v));
			} else {
				result.put(s, v);
			}
		}
		return result;
	}
	public static void main(String[] args) throws Exception
	{
		 StoreRecommendation sr = new StoreRecommendation();
		 //sr.maprecommendationdata();
		 sr.getdataforcsv();
	}
	public void maprecommendationdata()
	{
		collection = db.getCollection("reviewsdata");		
		DBCursor cursor = collection.find();
		JSONArray jsonarray=new JSONArray();
		StoreRecommendation sr=new StoreRecommendation();
		while (cursor.hasNext()) {
				DBObject dbo = cursor.next();
				JSONObject jsonobj= (JSONObject) JSONObject.wrap(dbo);
				JSONObject newjsonobj= new JSONObject();
			    long numericuser_id = sr.stringtolong(jsonobj.get("user_id").toString());
			    long numericbusiness_id = sr.stringtolong(jsonobj.get("business_id").toString());
			    newjsonobj.put("user_id", jsonobj.get("user_id"));
				newjsonobj.put("numericuser_id", numericuser_id );
				newjsonobj.put("business_id", jsonobj.get("business_id"));
				newjsonobj.put("numericbusiness_id", numericbusiness_id);
				newjsonobj.put("stars", jsonobj.get("stars"));
				jsonarray.put(newjsonobj);
		}
		System.out.println(jsonarray.length());
		cursor.close();
		sr.store(jsonarray);
	}
	public long stringtolong(String id)
	{
		String str = id;
	    StringBuilder sb = new StringBuilder();
	    for (char c : str.toCharArray())
	    sb.append((long)c);
	    BigInteger mInt = new BigInteger(sb.toString());
	    long numeric_id = mInt.longValue();
	    return numeric_id; 
	}
	
	public void getdataforcsv()
	{
				DBCursor cursor = collection.find();
				JSONArray jsonarray=new JSONArray();
				while (cursor.hasNext()) {
					DBObject dbo = cursor.next();
					JSONObject jsonobj= (JSONObject) JSONObject.wrap(dbo);
					jsonarray.put(jsonobj);
					System.out.println(jsonobj.get("user_id"));
				}
				System.out.println(jsonarray.length());
				toCSV toCsv=new toCSV();
				toCsv.convert(jsonarray);
	}
	
	public String mapbackbusinessidrecommendeddata(Long numericbusiness_id)
	{
		collection = db.getCollection("recommendationdata");		
		BasicDBObject query = new BasicDBObject("numericbusiness_id", numericbusiness_id);
				DBCursor cursor = collection.find(query);
				cursor.limit(1);
				String business_id=null;
				while (cursor.hasNext()) {
					DBObject dbo = cursor.next();
					business_id=dbo.get("business_id").toString();
				}
				return business_id;
				
	}
	
	public String mapbackuseridrecommendeddata(Long numericuser_id)
	{
		collection = db.getCollection("recommendationdata");
				BasicDBObject query = new BasicDBObject("numericuser_id", numericuser_id);
				DBCursor cursor = collection.find(query);
				cursor.limit(1);
				String user_id=null;
				while (cursor.hasNext()) {
					DBObject dbo = cursor.next();
					user_id=dbo.get("user_id").toString();
				}
				return user_id;
	}
	
public void recommendeddatastore(JSONObject jsonObject) {
		
		collection = db.getCollection("recommendeddata");
		DBObject dbo = (DBObject) JSON.parse(jsonObject.toString());
		
		// System.out.println("Encoding json object to dbobject...");
		DBObject dboObject = encode(jsonObject);
		// System.out.println("Encoded json object to dbobject...");
		// System.out.println("Storing json object in mongodb...");
		System.out.println(dbo.get("user_id"));
        System.out.println("\t\t\t"+dbo.get("recommendation1"));
        System.out.println("\t\t\t"+dbo.get("recommendation2"));
        System.out.println("\t\t\t"+dbo.get("recommendation3"));
		collection.insert(dbo);
		// System.out.println("Stored json object in Mongodb");
		// System.out.println("\n");
		}
}

	



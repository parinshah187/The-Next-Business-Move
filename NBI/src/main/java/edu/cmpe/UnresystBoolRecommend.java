package edu.cmpe;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.cli2.OptionException;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.json.JSONObject;


public class UnresystBoolRecommend {
    
    public static void main(String[] args) throws FileNotFoundException, TasteException, IOException, OptionException {
        
          
        File ratingsFile1 = new File("./data/recomendationinput.csv");        
        FileDataModel model = new FileDataModel(ratingsFile1);
        
        
        // create a simple recommender on our data
        SlopeOneRecommender sr=new SlopeOneRecommender(model);
        
        CachingRecommender cachingRecommender = new CachingRecommender(sr);
       
        
        // print output to a text file for further processing
        //PrintWriter out = new PrintWriter(new FileWriter("./data/recommendations.txt"));
        StoreRecommendation str=new StoreRecommendation();
        
        // for all users
        for (LongPrimitiveIterator it = model.getUserIDs(); it.hasNext();)
        {
            JSONObject userrecommendation = new JSONObject();
        	long userId = it.nextLong();
        	
           String user_id= str.mapbackuseridrecommendeddata(userId);
           
           userrecommendation.put("user_id", user_id); 
           // get the recommendations for the user
    //        long user1=userIdMap.get(userId);
            List<RecommendedItem> recommendations = cachingRecommender.recommend(userId, 3);
            
            // if empty write something
            if (recommendations.size() == 0){
                
            	System.out.print("User ");
                System.out.print(userId);
                System.out.println(": no recommendations");
            }
            //String fileReco="";                
            // print the list of recommendations for each
            /*System.out.print("User ");
            System.out.print(userId);*/
            int count=0;
            String business_id=null;	
            for (RecommendedItem recommendedItem : recommendations) {
                
               /* System.out.print(":\t\t ");
                System.out.println(recommendedItem.getItemID() );*/
                business_id=str.mapbackbusinessidrecommendeddata(recommendedItem.getItemID());
                count++;
                userrecommendation.put("recommendation"+count, business_id); 
            }
            
            str.recommendeddatastore(userrecommendation);
            
            
            //System.out.println("User : "+userId +"\t"+ "Recommendations: "+recommendations);
        }        
    }
}
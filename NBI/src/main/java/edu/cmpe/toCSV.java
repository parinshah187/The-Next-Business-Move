package edu.cmpe;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class toCSV {
	
	static short rowcount;
	static int colcount;
	static FileOutputStream out = null;
	static File f;
	//static HSSFWorkbook workbook;
	//static HSSFSheet sheet;
	
	
	public void convert(JSONArray jsonArray){
		
		try {        	        				
	        File f1 = new File("./data/recomendationinput.csv");
	        if(f1.exists()){
	        	if(f1.delete()){
	        		System.out.println("Existing file deleted.");
	        	}
	        }
	        System.out.println("Creating new file..");
			f1.createNewFile();
			out = new FileOutputStream(f1, true);
			
			byte[] b = null;

			for(int i = 0; i < jsonArray.length(); i++){
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				String content = "";
						content = content + jsonObj.get("numericuser_id") + ","+ jsonObj.get("numericbusiness_id") + ","+ jsonObj.get("stars") +"\n";						
				
            	b = content.getBytes();				
        		out.write(b);        		
			}			
			System.out.println("Converted to csv successfully");
			out.close();
		} catch (IOException e) {
    			e.printStackTrace();
    			System.out.println("Exception thrown while writing data in excel file");
    	}
	}
	
}


package com.ridlr.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.simple.parser.JSONParser;

import org.codehaus.jackson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int a=5;
		try {
			
////		2017-08-01 10:02:37.0
		String startDate="2017-08-31 06:02:22";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = sdf.parse(startDate);
		java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime()); 
//		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   java.util.Date date1 = new java.util.Date();
		   java.sql.Timestamp sqlStartDate1 = new java.sql.Timestamp(date1.getTime()); 
		   System.out.println(sqlStartDate);
		   System.out.println(sqlStartDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   		
		
//		JSONParser parser = new JSONParser();
//
//	        try {         
//	            URL url = new URL("http://operations2.ridlr.in:88/upsrtcapi/transaction-status?method=TICKET&fromdate=2017-08-31&todate=2017-09-01"); // URL to Parse
//	            URLConnection yc = url.openConnection();
//	            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
//	            
//	            String inputLine;
//	            while ((inputLine = in.readLine()) != null) {               
//	            	org.json.simple.JSONObject a =  (org.json.simple.JSONObject) parser.parse(inputLine);
//	            	org.json.simple.JSONArray arr = (org.json.simple.JSONArray) a.get("data");
//	                // Loop through each item
//	                for (Object o : arr) {
//	                	org.json.simple.JSONObject tutorials = (org.json.simple.JSONObject) o;
//
//	                    String id =   tutorials.get("item_type").toString();
//	                    System.out.println("Post ID : " + id);
//
//	                    String title = tutorials.get("total_amount").toString();
//	                    System.out.println("Post Title : " + title);
//
//	                    System.out.println("\n");
//	                }
//	            }
//	            in.close();
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        } catch (org.json.simple.parser.ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            
	
		
	}

}

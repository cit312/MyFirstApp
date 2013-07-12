package com.example.myfirstapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.ArrayList;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;

public class HTTP_URL_JSON_Test {

//	public static void main(String[] args) {
//		HTTP_URL_JSON_Test jsonWeather = new HTTP_URL_JSON_Test();
//		HashMap weather = new HashMap();
//		weather = (HashMap) jsonWeather.getWeather();
//		
////		System.out.println("--------------");
////		System.out.println(weather);
//	}
	
	public HashMap getWeather() {

		// creating URL to a string

		String url = "http://api.worldweatheronline.com/free/v1/weather.ashx?q=83440&format=json&num_of_days=5&date=today&key=fqdenqevfzd2bdd53bcw4pp3";
		String json = new String();
		HashMap amap = new HashMap();

		int timeout = 500;
		int status;
		// calls the url from the string and creates a connection
		try {
			URL u = new URL(url);
			HttpURLConnection c = (HttpURLConnection) u.openConnection();

			JSONInputStream InStream = new JSONInputStream(c.getInputStream());
			// being read as an object
			// first level hash
			// casting instream whatever comes from there to a hash map
			amap = (HashMap) InStream.readObject();
			amap = (HashMap) amap.get("data");
			
			HashMap current_condition = (HashMap) ((ArrayList) amap.get("current_condition")).get(0);
			HashMap second_day = (HashMap) ((ArrayList) amap.get("weather")).get(0);
			HashMap third_day = (HashMap) ((ArrayList) amap.get("weather")).get(1);
			HashMap fourth_day = (HashMap) ((ArrayList) amap.get("weather")).get(1);
			HashMap fifth_day = (HashMap) ((ArrayList) amap.get("weather")).get(1);
			
//			System.out.println(amap);
//			System.out.println(current_condition.get("temp_F"));
//			System.out.println(second_day.get("tempMaxF"));

//			// second level hash
//			HashMap secondlvl = new HashMap();
//			secondlvl = (HashMap) amap.get("data");
//			// Today
//			System.out.println("Today: ");
//
//			// third level array casted into a hash
//			ArrayList thirdlvl = (java.util.ArrayList) secondlvl
//					.get("current_condition");
//			HashMap weathercondition = (HashMap) thirdlvl.get(0);
//
//			System.out.println("Temperature: " + weathercondition.get("temp_F")
//					+ "°F");
//			System.out.println("Wind Speed: "
//					+ weathercondition.get("windspeedMiles") + " mph");
//			System.out.println("Cloud Cover: "
//					+ weathercondition.get("cloudcover"));
//			System.out.println("Precipitation: "
//					+ weathercondition.get("precipMM"));
//
//			// weather loop to go through the weather array and pick out each
//			// hashmap date
//
//			ArrayList weatherArray = (ArrayList) secondlvl.get("weather");
//
//			for (int i = 1; i < weatherArray.size(); i++) {
//				HashMap three = (HashMap) weatherArray.get(i);
//
//				System.out.println(three.get("date"));
//				System.out.println("Max Temp: " + three.get("tempMaxF") + "°F");
//				System.out.println("Wind Speed: " + three.get("windspeedMiles")
//						+ " mph");
//
//			}
		}

		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Bad URL");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException");
			e.printStackTrace();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(url);
		//System.out.println(json);
		return amap;
	}
}

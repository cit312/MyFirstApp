package com.example.myfirstapp;

public class TestMain {

	public static void main(String[] args) {
/*		//Incoming Data
		HashMap<String, String> fakeInfo = new HashMap<String, String>();
		fakeInfo.put("command", "createUser");
		fakeInfo.put("userName", "Johnny");
		fakeInfo.put("password", "Sally");
		//Collect Data and Handle it
		ApplicationController appcntrl = new ApplicationController();
		appcntrl.handleRequest(fakeInfo.get("command"), fakeInfo);   */
		
//		HashMap<String,Integer> numbers = new HashMap<String,Integer>();
//		numbers.put("one", 1);
//		numbers.put("two", 2);
//		numbers.put("three", 3);
//		
//		Client c = new Client(Json.toJson(numbers));
//		c.transmit();
		
		Client c = new Client();
		c.transmit();
	}

}
package com.example.myfirstapp;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	static final String EXTRA_MESSAGE = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		//For Debugging
		System.out.println("sendMessage...");
		
		//Debugging: create data to send to server
		HashMap<String,String> numbers = new HashMap<String,String>();
		numbers.put("userID", "16");
		numbers.put("two", "another");
		numbers.put("three", "The third thing");
		
		//Put data into CommBean (the command as String and data as hashmap)
		CommBean data = new CommBean("getPivots");
		data.setData(numbers);
		
		//Start the socket
		Client c = new Client(data);
		HashMap returnedData = c.transmit();
		
		//Get returned data
		returnedData = (HashMap) returnedData.get("data");
		
		//Debugging
		System.out.println("--THE RETURNED DATA --");
		System.out.println(returnedData);
		
		//Build and start intent
	    Intent intent = new Intent(this, DisplayMessageActivity.class);
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    String message = editText.getText().toString();
	    //String message = (String) returnedData.get("Pivot 1");
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}

}

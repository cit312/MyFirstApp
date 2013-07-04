package com.example.myfirstapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	static final String EXTRA_MESSAGE = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
		
		//Check if error
		String error = (String) returnedData.get("error");
		
		//Get returned data
		returnedData = (HashMap) returnedData.get("data");
		
		//The loop
		for (String key : new HashSet<String>(returnedData.keySet())) {
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
//	        TextView txt1 = new TextView(this);
//	        txt1.setTextSize(40);
//	        txt1.setText(key);
	        
	        Button btn = new Button(this); 
	        btn.setText(key); 
	        linearLayout.addView(btn);
	        
	        final String attr3 = (String) ((HashMap)returnedData.get(key)).get("Attr3");
	        
	        btn.setOnClickListener(new Button.OnClickListener() {  
	            public void onClick(View v)
	            {
	                System.out.println(attr3);
	            }
	         });
	        
	        //linearLayout.setBackgroundColor(Color.TRANSPARENT);
//	        linearLayout.addView(txt1);
	        
			//System.out.println(((HashMap)returnedData.get(key)).get("Attr3"));
		}
		
		
//		HashMap firstPivot = (HashMap) returnedData.get("Pivot 1");
//		HashMap secondPivot = (HashMap) returnedData.get("Pivot 2");
//		
//				
//		//Debugging
//		System.out.println(returnedData.keySet());
//		System.out.println("--THE RETURNED DATA --");
//		System.out.println(returnedData);
//		
//	    String message = (String) firstPivot.get("Attr3");
//	    String message2 = (String) secondPivot.get("Attr3");
//
//        
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
//        TextView txt1 = new TextView(this);
//        txt1.setTextSize(40);
//        txt1.setText(message);
//        TextView txt2 = new TextView(this);
//        txt2.setTextSize(40);
//        txt2.setText(message2);
//        
//        //linearLayout.setBackgroundColor(Color.TRANSPARENT);
//        linearLayout.addView(txt1);
//        linearLayout.addView(txt2);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		//Build and start intent
	    Intent intent = new Intent(this, DisplayMessageActivity.class);
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}

}

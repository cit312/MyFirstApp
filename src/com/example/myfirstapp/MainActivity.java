package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.telephony.TelephonyManager;
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
		
		//***** Get Weather Stuff
		HTTP_URL_JSON_Test jsonWeather = new HTTP_URL_JSON_Test();
		HashMap weather = new HashMap();
		weather = (HashMap) jsonWeather.getWeather();

		HashMap current_condition = (HashMap) ((ArrayList) weather.get("current_condition")).get(0);
		
		Button p1_button = (Button) findViewById(R.id.weatherBtn);
		p1_button.setText("Weather: " + current_condition.get("temp_F") + " Degrees");
		//***********************************
		
		//For Debugging
		System.out.println("sendMessage...");
		
		//Get user phone number
		String mPhoneNumber = null;
		TelephonyManager tMgr =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);    
        try {
		mPhoneNumber = tMgr.getLine1Number(); 
		
        } catch (Exception ex){
        	System.out.println(ex.getMessage());
        }
        

				
		//Data to send to server
		HashMap<String,String> numbers = new HashMap<String,String>();
		numbers.put("number", mPhoneNumber);
		
		//Put data into CommBean (the command as String and data as hashmap)
		CommBean dataBean = new CommBean("getAppUsers");
		dataBean.setData(numbers);
						
		//Start the socket
		Client c = new Client(dataBean);
		HashMap returnedData = c.transmit();
		
		System.out.println("---------------");
		System.out.println(returnedData);
		
		//Check if error
		String error = (String) returnedData.get("error");
		
		//Get returned data
		returnedData = (HashMap) returnedData.get("data");
		HashMap pivots = new HashMap();
		pivots = (HashMap) returnedData.get("Pivots");
		
		System.out.println(pivots);
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
		//Loop through pivots and add em in
		Iterator it = pivots.entrySet().iterator();
		while (it.hasNext()){
			final Map.Entry pivot = (Map.Entry)it.next();
			
			//System.out.println("**********");
			//System.out.println(pivot.getValue());
			
			//Collect Vars
			String pivotName = (String) pivot.getKey();
			//String pivot_notes = (String) ((HashMap) pivot.getValue()).get("Notes");
			//String pivot_id =  (String) ((HashMap<String,String>) pivot.getValue()).get("id");
			//System.out.println("ID: " + ((HashMap) pivot.getValue()).get("id"));
			//System.out.println(pivot.getKey() + " = " + pivot.getValue());
			
			TextView txt1 = new TextView(this);
        
			//Dynamicly add the buttons
			Button btn = new Button(this); 
			btn.setText(pivotName); 
			linearLayout.addView(btn);
			
			//Add button listener
	        btn.setOnClickListener(new Button.OnClickListener() {
	            public void onClick(View v)
	            {
//	                System.out.println("HURRRR----------");
//	                System.out.println(pivot.getValue());
	                Intent intent = new Intent(MainActivity.this, PivotInfo.class);
	        	    intent.putExtra("PIVOT_DATA", (HashMap) pivot.getValue());
	        	    //intent.putExtra("linearLayout", (CharSequence) findViewById(R.id.root));
	        	    startActivity(intent);
	            }
	        });
			
	        it.remove(); // avoids a ConcurrentModificationException
		}
		
		
		
		
		
//		for (String key : new HashSet<String>(((HashMap) returnedData.get("Pivots")).keySet())) {	
////	        TextView txt1 = new TextView(this);
////	        txt1.setTextSize(40);
////	        txt1.setText(key);
//	        
//	        Button btn = new Button(this); 
//	        btn.setText(key); 
//	        linearLayout.addView(btn);
//	        
//	        final String id = (String) ((HashMap)((HashMap) returnedData.get("Pivots")).get(key)).get("ID");
//	        final HashMap pivotHash = (HashMap)((HashMap) ((HashMap) returnedData.get("Pivots")).get(key));
//	        
//	        System.out.println("***************");
//	        System.out.println(id);
//	        System.out.println(pivotHash);
//	        
//	        btn.setOnClickListener(new Button.OnClickListener() {
//	            public void onClick(View v)
//	            {
//	            	//Debugging
//	                System.out.println(id);
//	                System.out.println(pivotHash);
//	                
//	                Intent intent = new Intent(MainActivity.this, PivotInfo.class);
//	        	    intent.putExtra("PIVOT_DATA", pivotHash);
//	        	    //intent.putExtra("linearLayout", (CharSequence) findViewById(R.id.root));
//	        	    startActivity(intent);
//	            }
//	         });
//	        
//	        //linearLayout.setBackgroundColor(Color.TRANSPARENT);
////	        linearLayout.addView(txt1);
//	        
//			//System.out.println(((HashMap)returnedData.get(key)).get("Attr3"));
//		}
		
		
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
//        System.out.println("HURR" + linearLayout);
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
	public void newPivot(View view) {
		//Build and start intent
	    Intent intent = new Intent(this, NewPivotActivity.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		//This sucks doing again...
		HTTP_URL_JSON_Test jsonWeather = new HTTP_URL_JSON_Test();
		HashMap weather = new HashMap();
		weather = (HashMap) jsonWeather.getWeather();
		
		//Build and start intent
	    Intent intent = new Intent(this, WeatherActivity.class);
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
	    intent.putExtra("WEATHER", (HashMap) weather);
	    startActivity(intent);
	}

}

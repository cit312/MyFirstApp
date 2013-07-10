package com.example.myfirstapp;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;

public class NewLogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_log);
	}
	
	/** Called when the user clicks the new pivot button */
	public void createLog(View view) {
		//Build and start intent
	    Intent intent = new Intent(this, MainActivity.class);
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    
	  //Get user phone number
	  String mPhoneNumber = null;
	  TelephonyManager tMgr =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);    
	      try {
	        mPhoneNumber = tMgr.getLine1Number(); 
	  		
	     } catch (Exception ex){
	        System.out.println(ex.getMessage());
	    }
	    
	    HashMap<String,String> data = new HashMap<String,String>();
		data.put("userID", "16");
		data.put("number", mPhoneNumber);
		
		//Put data into CommBean (the command as String and data as hashmap)
		CommBean dataBean = new CommBean("createLog");
		dataBean.setData(data);
		
		//Start the socket
		Client c = new Client(dataBean);
		HashMap returnedData = c.transmit();
	    
	    startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_log, menu);
		return true;
	}

}

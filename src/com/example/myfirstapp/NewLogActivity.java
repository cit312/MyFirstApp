package com.example.myfirstapp;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class NewLogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("NEW LOG Activity");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_log);
		
	}
	
	/** Called when the user clicks the new pivot button */
	public void createLog(View view) {
		//Get vars
		Intent intent2 = getIntent();
		HashMap pivotData = (HashMap) intent2.getSerializableExtra("PIVOT_DATA");
		//System.out.println(pivotData.get("pivot_id"));
		//Intent intent2 = getIntent();
		//String pivot_id = intent2.getStringExtra("pivot_id");
		//System.out.println("-------- " + pivot_id);
		
		//Build and start intent
	    Intent intent = new Intent(this, MainActivity.class);
	    EditText editText = (EditText) findViewById(R.id.editText1);
	    String newLog = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
	  //Get user phone number
	  String mPhoneNumber = null;
	  TelephonyManager tMgr =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);    
	      try {
	        mPhoneNumber = tMgr.getLine1Number(); 
	  		
	     } catch (Exception ex){
	        System.out.println(ex.getMessage());
	    }
	    
	    HashMap data = new HashMap();
		data.put("pivotID", pivotData.get("pivot_id"));
		data.put("newLog", newLog);
		data.put("number", mPhoneNumber);
		
		System.out.println("----------------");
		System.out.println(data);
		
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

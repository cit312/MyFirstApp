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
		System.out.println("sendMessage...");
		HashMap<String,String> numbers = new HashMap<String,String>();
		numbers.put("one", "more");
		numbers.put("two", "another");
		numbers.put("three", "The third thing");
		
		CommBean data = new CommBean("Testing");
		data.setData(numbers);
		
		Client c = new Client(data);
		HashMap returnedData = c.transmit();
		
		
		
	    Intent intent = new Intent(this, DisplayMessageActivity.class);
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}

}

package com.example.myfirstapp;

import java.util.HashMap;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class PivotInfo extends Activity {

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Debugging
    	System.out.println("PivotInfo Class.");
    	
        super.onCreate(savedInstanceState);

        // Get the hash from the intent
        Intent intent = getIntent();
        System.out.println("ONE");
        HashMap pivotData = (HashMap)intent.getSerializableExtra("PIVOT_DATA");
        System.out.println(pivotData);
        
        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText((String) pivotData.get("Attr3"));
        System.out.println("TWO");

        // Set the text view as the activity layout
        setContentView(textView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

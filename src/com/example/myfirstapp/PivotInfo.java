package com.example.myfirstapp;

import java.util.HashMap;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PivotInfo extends Activity {

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Debugging
    	System.out.println("PivotInfo Class.");
    	setContentView(R.layout.activity_display_message);
    	
        super.onCreate(savedInstanceState);

        // Get the hash from the intent
        Intent intent = getIntent();
        HashMap pivotData = (HashMap)intent.getSerializableExtra("PIVOT_DATA");
        System.out.println("UNO");
        //LinearLayout linearLayout = (LinearLayout) intent.getCharSequenceExtra("linearLayout");
        //System.out.println("HURR" + linearLayout);
        System.out.println(pivotData);
        
//        LayoutInflater inflater;
//        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);              
//        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_main, null);
//        View linearLayout2 = getLayoutInflater().inflate(R.layout.activity_main, null);
//        LinearLayout linearLayout = (LinearLayout) linearLayout2.findViewById(R.id.root);
          LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root2);
          
          System.out.println("HEYA: " + linearLayout);
        
        // Create the Attr2 text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setTextAppearance(this, R.style.CodeFont);
        textView.setText((String) pivotData.get("Attr2"));
        
     // Create the Attr3 text view
        TextView textView2 = new TextView(this);
        textView2.setTextSize(40);
        textView2.setTextAppearance(this, R.style.CodeFont);
        textView2.setText((String) pivotData.get("Attr3"));
        //textView2.setText("HERES SOME TEXT");
        
        // Set the text view as the activity layout
        //setContentView(textView);
        
        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
        //System.out.println("HURR" + linearLayout);
        linearLayout.addView(textView2);
        linearLayout.addView(textView);
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

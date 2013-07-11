package com.example.myfirstapp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PivotInfo extends Activity {

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Debugging
    	System.out.println("PivotInfo Class.");
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.pivot_info);

        // Get the hash from the intent
        Intent intent = getIntent();
        HashMap pivotData = (HashMap) intent.getSerializableExtra("PIVOT_DATA");
        
        //LinearLayout linearLayout = (LinearLayout) intent.getCharSequenceExtra("linearLayout");
        //System.out.println("HURR" + linearLayout);
        //System.out.println(pivotData);
        
//        LayoutInflater inflater;
//        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);              
//        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_main, null);
//        View linearLayout2 = getLayoutInflater().inflate(R.layout.activity_main, null);
//        LinearLayout linearLayout = (LinearLayout) linearLayout2.findViewById(R.id.root);
          LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root2);
        
        //Loop through all logs/notes
        Integer count = 0; // For a striped view
  		Iterator it = ((HashMap) pivotData.get("Notes")).entrySet().iterator();
  		while (it.hasNext()){
  			Map.Entry log = (Map.Entry)it.next();
  			String theNote = (String) ((HashMap) log.getValue()).get("note");
  			
			//Add to view
  			if (count % 2 == 0) {
  				TextView textView = new TextView(this);
		        textView.setTextSize(200);
		        textView.setTextAppearance(this, R.style.Log);
		        textView.setBackgroundColor(0xff808080);
		        textView.setText(theNote);
		        linearLayout.addView(textView);
  			} else {
  				TextView textView2 = new TextView(this);
		        textView2.setTextSize(200);
		        textView2.setTextAppearance(this, R.style.Log2);
		        textView2.setBackgroundColor(0xff000000);
		        textView2.setText(theNote);
		        linearLayout.addView(textView2);
  			}
			count++;
	        
	        
	        
//	        TextView ruler = new TextView(this);
//	        ruler.setBackgroundColor( 0xff000000);
//	        ruler.setTextAppearance(this, R.style.LineBreak);
//	        linearLayout.addView(ruler);
	        
  			
  		}
          
          
//       Iterator it = pivotData.entrySet().iterator();
//  		while (it.hasNext()){
//  			Map.Entry log = (Map.Entry) it.next();
//  			HashMap temp = (HashMap) log.getValue();
//  			
//  			Iterator it2 = temp.entrySet().iterator();
//  	  		while (it2.hasNext()){
//  	  			Map.Entry theLog = (Map.Entry)it2.next();
//  	  			
//  	  			System.out.println("HURRRR333----------");
//  	  			System.out.println(((HashMap) theLog.getValue()).get("note"));
//  	  			
//  	  			String theNote = (String) ((HashMap) theLog.getValue()).get("note");
//  	  			System.out.println("theNote: " + theNote);
//  	  			//Add to view
////  	  			TextView textView = new TextView(that);
////  	  	        textView.setTextSize(200);
////  	  	        textView.setTextAppearance(that, R.style.CodeFont);
////  	  	        textView.setText((String) theNote);
////  	  	        setContentView(textView);
////  	  	        linearLayout.addView(textView);
//  	  			
//  	  		//Dynamicly add button
////  				Button btn = new Button(that); 
////  				btn.setText(theNote); 
////  				linearLayout.addView(btn);
//  	  	        
//  	  		}
//  		}
          
          
        // Create the Attr2 text view
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setTextAppearance(this, R.style.CodeFont);
//        textView.setText("pivotNotes");
        
     // Create the Attr3 text view
//        TextView textView2 = new TextView(this);
//        textView2.setTextSize(40);
//        textView2.setTextAppearance(this, R.style.CodeFont);
//        textView2.setText("Attr3");
        //textView2.setText("HERES SOME TEXT");
        
        // Set the text view as the activity layout
        //setContentView(textView);
        
        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
        //System.out.println("HURR" + linearLayout);
//        linearLayout.addView(textView2);
        //linearLayout.addView(textView);
    }
    
    
	/** Called when the user clicks the new Log button */
	public void newLog(View view) {
		//Build and start intent
	    Intent intent = new Intent(this, NewLogActivity.class);
	    startActivity(intent);
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

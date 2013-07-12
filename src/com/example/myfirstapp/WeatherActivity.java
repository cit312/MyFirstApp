package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);
		
        Intent intent = getIntent();
        HashMap weather = (HashMap) intent.getSerializableExtra("WEATHER");
        
        HashMap current_condition = (HashMap) ((ArrayList) weather.get("current_condition")).get(0);
		HashMap second_day = (HashMap) ((ArrayList) weather.get("weather")).get(1);
		HashMap third_day = (HashMap) ((ArrayList) weather.get("weather")).get(2);
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root3);
		
		TextView day = new TextView(this);
		day.setBackgroundColor(0xff808080 );
		day.setText("Today (Current)");
		
		TextView desc = new TextView(this);
		desc.setText((CharSequence) ((HashMap) ((ArrayList) current_condition.get("weatherDesc")).get(0)).get("value"));
		
		TextView temp = new TextView(this);
		temp.setText("Temp (F): " + current_condition.get("temp_F"));
		
		TextView hum = new TextView(this);
		hum.setText("Humidity: " + current_condition.get("humidity"));
		
		TextView windDir = new TextView(this);
		windDir.setText("Wind Direction: " + current_condition.get("winddir16Point"));
		
		TextView windSpeed = new TextView(this);
		windSpeed.setText("Wind Speed (mph): " + current_condition.get("windspeedMiles"));
		
		linearLayout.addView(day);
		linearLayout.addView(desc);
		linearLayout.addView(temp);
		linearLayout.addView(hum);
		linearLayout.addView(windDir);
		linearLayout.addView(windSpeed);
		
		TextView day2 = new TextView(this);
		day2.setBackgroundColor(0xff808080 );
		day2.setText("Tommorow");
		
		TextView desc2 = new TextView(this);
		desc2.setText((CharSequence) ((HashMap) ((ArrayList) second_day.get("weatherDesc")).get(0)).get("value"));
		
		TextView temp2 = new TextView(this);
		temp2.setText("Max Temp (F): " + second_day.get("tempMaxF"));
		
		TextView windDir2 = new TextView(this);
		windDir2.setText("Wind Direction: " + second_day.get("winddir16Point"));
		
		TextView windSpeed2 = new TextView(this);
		windSpeed2.setText("Wind Speed (mph): " + second_day.get("windspeedMiles"));
		
		linearLayout.addView(day2);
		linearLayout.addView(desc2);
		linearLayout.addView(temp2);
		linearLayout.addView(windDir2);
		linearLayout.addView(windSpeed2);
		
		TextView day3 = new TextView(this);
		day3.setBackgroundColor(0xff808080 );
		day3.setText((CharSequence) third_day.get("date"));
		
		TextView desc3 = new TextView(this);
		desc3.setText((CharSequence) ((HashMap) ((ArrayList) third_day.get("weatherDesc")).get(0)).get("value"));
		
		TextView temp3 = new TextView(this);
		temp3.setText("Max Temp (F): " + third_day.get("tempMaxF"));
		
		TextView windDir3 = new TextView(this);
		windDir3.setText("Wind Direction: " + third_day.get("winddir16Point"));
		
		TextView windSpeed3 = new TextView(this);
		windSpeed3.setText("Wind Speed (mph): " + third_day.get("windspeedMiles"));
		
		linearLayout.addView(day3);
		linearLayout.addView(desc3);
		linearLayout.addView(temp3);
		linearLayout.addView(windDir3);
		linearLayout.addView(windSpeed3);
//		
//		LinearLayout day2Layout = (LinearLayout) findViewById(R.id.day2);
//		TextView text2 = new TextView(this);
//		text2.setText("Max Temp: " + second_day.get("tempMaxF"));
//		day2Layout.addView(text2);
//		
//		LinearLayout day3Layout = (LinearLayout) findViewById(R.id.day3);
//		TextView text3 = new TextView(this);
//		text3.setText("Max Temp: " + third_day.get("tempMaxF"));
//		day3Layout.addView(text2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

}

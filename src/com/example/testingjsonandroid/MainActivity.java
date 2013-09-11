package com.example.testingjsonandroid;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private LinearLayout root;
	private LayoutInflater inflater = null;

	TextView tv = null;
	JSONArray jsonArray = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		Context context = getApplicationContext();
		CharSequence title = null;
		CharSequence content = null;
		CharSequence meta = null;
		int duration = Toast.LENGTH_LONG;
		
//		LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View v = vi.inflate(R.layout.post, null);
//		tv = (TextView) v.findViewById(R.id.title);
//		tv.setText("WOOOOOHOOOO");
//		View insertPoint = findViewById(R.id.scrollViewLinearWrapper);
//		((ViewGroup) insertPoint).addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		
		try {
			jsonArray = new FetchPosts().execute("http://192.168.1.76/backend/modules/posts/fetchposts.php").get();
			for(int i = 0; i < jsonArray.length(); i++){
				title = (CharSequence) jsonArray.getJSONObject(i).get("title");
				content = (CharSequence) jsonArray.getJSONObject(i).get("text");
				meta = (CharSequence) jsonArray.getJSONObject(i).get("author");
				create(title,content,meta);
			}


		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void create(CharSequence sTitle, CharSequence sContent, CharSequence sMeta){
		inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		LinearLayout.LayoutParams postContentParams
		= new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				0.0f);
	
		View v = inflater.inflate(R.layout.post, null);
		View insertPoint = findViewById(R.id.scrollViewLinearWrapper);
		
		TextView title = (TextView) v.findViewById(R.id.title);
		TextView content = (TextView) v.findViewById(R.id.content);
		TextView meta = (TextView) v.findViewById(R.id.meta);
		
		title.setText(sTitle);
		content.setText(sContent);
		meta.setText(sMeta);
		
		((ViewGroup) insertPoint).addView(v, 0, postContentParams);
		
	}

}

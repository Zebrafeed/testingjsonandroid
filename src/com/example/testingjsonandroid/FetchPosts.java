package com.example.testingjsonandroid;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;

public class FetchPosts extends AsyncTask<String, Void, JSONArray> {
	
	JsonFetcher jsonFetcher = new JsonFetcher();
	JSONArray jArray = new JSONArray();
	@Override
	protected JSONArray doInBackground(String... urls) {
		String url = urls[0];
		try {
			jArray = jsonFetcher.readJSONArrayFromUrl(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jArray;
	}



}

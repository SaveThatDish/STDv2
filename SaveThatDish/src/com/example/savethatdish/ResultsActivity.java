package com.example.savethatdish;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ResultsActivity extends Activity {
	 
	ListView listView;
	List<JSONObject> results;
	List<String> addresses;
	List<String> names;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		
		listView = (ListView)findViewById(R.id.searchResults);
		results = SearchActivity.returnResults();
		addresses = new ArrayList<String>();
		names = new ArrayList<String>();

		for(JSONObject j : results) {
			try {
				JSONObject location = (JSONObject) j.get("location");
				JSONArray address = (JSONArray) location.get("display_address");
				String city = (String) location.get("city");
				addresses.add(address.get(0) + ", " + city);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		checkParseRestaurant(addresses);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent restaurant = new Intent(ResultsActivity.this, RestaurantActivity.class);
				startActivity(restaurant);				
			}

		});

	}
	
	public void checkParseRestaurant(List<String> addresses) {		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurant");
		query.whereContainedIn("short_address", addresses);
		query.findInBackground(new FindCallback<ParseObject>() {
			@SuppressWarnings("unchecked")
			public void done(List<ParseObject> restaurants, ParseException e) {
				if (e == null) {
					new ResultsTask().execute(restaurants);
				} 
				else {
				}
            }
		});
	}
	
	public void listThings(List<ParseObject> restaurants) {
		for(ParseObject o : restaurants) {
			names.add((String) o.get("name"));
		}
	}
	
	public class ResultsTask extends AsyncTask<List<ParseObject>, Void, Void> {
		@Override
		protected Void doInBackground(List<ParseObject>... params) {
			listThings(params[0]);
			return null;
		}
		
		protected void onPostExecute(Void result) {
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				ResultsActivity.this, android.R.layout.simple_list_item_1, names);
			listView.setAdapter(arrayAdapter);
		}
	}
}
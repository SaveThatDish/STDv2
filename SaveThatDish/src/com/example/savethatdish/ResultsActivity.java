package com.example.savethatdish;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
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
		
		new ResultsTask().execute();
		
		/**listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				// TODO Auto-generated method stub	
			}		
		});*/
	}
	
	public void checkParseRestaurant(List<String> addresses) {		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurant");
		query.whereContainedIn("short_address", addresses);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> restaurants, ParseException e) {
				if (e == null) {
					listThings(restaurants);
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
	
	public class ResultsTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			checkParseRestaurant(addresses);
			return null;
		}
		
		protected void onPostExecute(Void result) {
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				ResultsActivity.this, android.R.layout.simple_list_item_1, names);
			listView.setAdapter(arrayAdapter);
		}
	}
}
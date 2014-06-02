package com.example.savethatdish;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends Activity {
	public static final String CONSUMER_KEY = "PYJ9fp4Zs357x8GKEcc2OA";
	public static String CONSUMER_SECRET = "Svw5yWnPK26_WYOrbkcvsC4PMNU";
	public static final String TOKEN = "_o14KmTq969arSh-BdJBIHIBLanS3h2J";
	public static final String TOKEN_SECRET = "MLFvYopfLQVy8YWpN7WObb8u_EA";
	
	private static List<JSONObject> results = new ArrayList<JSONObject>();
	private EditText searchText, locationText;
	
    public static double latitude;
    public static double longitude;
    
    float x1,x2;
    float y1, y2;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search);
		
		searchText = (EditText)findViewById(R.id.editTextSearchKeyword);
		locationText = (EditText)findViewById(R.id.editTextSearchLocation);
		
		ImageButton imageButton1 = (ImageButton)findViewById(R.id.searchButton);
		imageButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				results.clear();
				String query = searchText.getText().toString();
				String locationQuery = locationText.getText().toString();
				
				if (query != null && (query.length() != 0) && locationQuery != null 
						&& (locationQuery.length() != 0))
					//make sure the search had something in it
					new SearchTask().execute(query, locationQuery);
			}
		});
		
		/* CODE FOR A HAMBURGER CLICK */
        ImageButton hb = (ImageButton) findViewById(R.id.hamburger);
        hb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	startActivity(new Intent(SearchActivity.this, HamburgerActivity.class));
        	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
        }
        });
	}

	
	@Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        
   	    /* SWIPE YOUR FINGER LEFT TO RIGHT AND HAMBURGER MENU WILL OPEN */
        switch (touchevent.getAction())
        {
          case MotionEvent.ACTION_DOWN: 
          {
            x1 = touchevent.getX(); y1 = touchevent.getY();
            break;
          }
          case MotionEvent.ACTION_UP: 
          {
            x2 = touchevent.getX(); y2 = touchevent.getY(); 
            if (x1 < x2)//L to R swipe 
            {
         	  Intent intent = new Intent(SearchActivity.this, HamburgerActivity.class);
            	  startActivity(intent);     
        	      overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
            }
          }
        }
        return true;
    }

	
	public static List<JSONObject> returnResults() {
		return results;
	}
	
	public class SearchTask extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(String... arg0) {
			try {
				
				Yelp yelp = new Yelp(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
			    String response = yelp.search(arg0[0], "0", arg0[1]);
			    
			    JSONObject obj = new JSONObject(response);
			    JSONArray businesses = obj.getJSONArray("businesses");
			    
			    int n = businesses.length();
			    for(int i = 0; i < n; i++) {
			    	results.add(businesses.getJSONObject(i));
			    }
			    
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(Void result) {
			Intent results = new Intent(SearchActivity.this, ResultsActivity.class);
			startActivity(results);
		}
	}
}
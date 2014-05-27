package com.example.test;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.savethatdish.SearchActivity;
import com.example.savethatdish.Yelp;

public class YelpTest extends TestCase {
	
	Yelp yelp;
	private JSONArray businesses;

	protected void setUp() throws Exception {
		super.setUp();
		yelp = new Yelp(SearchActivity.CONSUMER_KEY, SearchActivity.CONSUMER_SECRET, SearchActivity.TOKEN, SearchActivity.TOKEN_SECRET);
		String response = yelp.search("burrito", "0", "la jolla");
		
		JSONObject obj = new JSONObject(response);
	    businesses = obj.getJSONArray("businesses");
	}
	
	public void testYelpSearchResponse() {
	    int n = businesses.length();
	    assertFalse(n == 0);
	    for(int i = 0; i < n; i++) {
	    	assertFalse(businesses.getJSONObject(i).getString("name").equals(""));
	    }
	}

}
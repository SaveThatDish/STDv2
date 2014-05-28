package com.example.savethatdish;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class RestaurantActivity extends Activity{
	
	private DishAdapter dishAdapter;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant);
		
		//Find the ID of the restaurant to display
		Intent intent = getIntent();
		String restaurantId = intent.getStringExtra("restaurant_id");
		
		// Create an adapter to bind the items with the view
		dishAdapter = new DishAdapter(this, R.layout.menu_item);
		ListView dishlist = (ListView) findViewById(R.id.dishes_list);
		dishlist.setAdapter(dishAdapter);		
		
		listDishes(restaurantId);
	}
	
/*Need to put this code whenever you click on a restaurant:
	Intent intent = new Intent("com.example.savethatdish.RestaurantActivity");
	intent.putExtra("restaurant_id", restaurant.getObjectId()); <-- restaurant is the parse object that was clicked
	startActivity(intent);
*/
	private void listDishes(String ID) {
		
		//Query all dishes in the database and choose the ones belonging to the right restaurant
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Dish");
		query.whereEqualTo("restaurant", ID);

		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> dishList, ParseException e) {
		        if (e == null) {
		        	//Add the results to the adapter to be displayed
		        	for(ParseObject a : dishList)
		        	{
		        		dishAdapter.add((Dish)a);
		        	}
		        } else {
		        	// TODO Throw some sort of error?
		        }
		    }
		});
	}
}
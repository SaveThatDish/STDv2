package com.example.savethatdish;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class RestaurantActivity extends Activity{
	
	private DishAdapter dishAdapter;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant);
		
		// Create an adapter to bind the items with the view
		dishAdapter = new DishAdapter(this, R.layout.menu_item);
		ListView dishlist = (ListView) findViewById(R.id.dishes_list);
		dishlist.setAdapter(dishAdapter);		

		//Find the ID of the restaurant to display
		Intent intent = getIntent();
		String restaurantId = intent.getStringExtra("restaurant_id");
		
		//Retrieve the restaurant ParseObject
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurant");
		query.getInBackground(restaurantId, new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (e == null) {
		    	setInfo(object);
				setNumFriends(object);
				setDistance(object);
				listDishes(object);
		        new ImageTask().execute(object);
		    } else {
		    	Log.w("TEST", "Failed :(");
		    	e.printStackTrace();
		    }
		  }
		});
				
	}
	
	private void setInfo(ParseObject restaurant) {
		//Set restaurant name
		TextView text = (TextView) findViewById(R.id.label_name);
		text.setText(restaurant.getString("name"));
		
		//Set restaurant rating
		//text = (TextView) findViewById(R.id.label_rating);
		//text.setText(restaurant.getString("rating"));

		//Set number of reviews
		//text = (TextView) findViewById(R.id.label_reviews);
		//text.setText(restaurant.getString("review_count"));

		//Set restaurant address
		text = (TextView) findViewById(R.id.label_address);
		text.setText(restaurant.getString("short_address"));
		/*
		//Set restaurant_logo or image
        InputStream is;
		try {
			is = (InputStream) new URL(restaurant.getString("yelp_image_url")).getContent();
	        Drawable d = Drawable.createFromStream(is, "src name");
	        ImageView image = (ImageView) findViewById(R.id.label_logo);
	        image.setImageDrawable(d);        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	private void setDistance(ParseObject object) {
		// TODO calculate distance from current location to the restaurant
		// set TestView on R.id.label_mile
	}
	
	private void setNumFriends(ParseObject object) {
		// TODO calculate number of friends who also want to visit this restaurant
		// set TextView on R.id.label_friend
	}
	
	/*Need to put this code whenever you click on a restaurant:
	Intent intent = new Intent("com.example.savethatdish.RestaurantActivity");
	intent.putExtra("restaurant_id", restaurant.getObjectId()); <-- restaurant is the parse object that was clicked
	startActivity(intent);
*/
	public class ImageTask extends AsyncTask<ParseObject, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(ParseObject... arg0) {
			Bitmap mIcon = null;
			InputStream newurl;
			try {
				newurl = (InputStream) new URL((String) arg0[0].get("yelp_image_url")).openStream();
				mIcon = BitmapFactory.decodeStream(newurl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mIcon;
		}
		
		protected void onPostExecute(Bitmap result) {
	        ImageView image = (ImageView) findViewById(R.id.label_logo);
			image.setImageBitmap(result);
		}
	}
	
	private void listDishes(ParseObject object) {
		
		//Query all dishes in the database and choose the ones belonging to the right restaurant
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Dish");
		query.whereEqualTo("restaurant", object);

		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> dishList, ParseException e) {
		        if (e == null) {
		        	//Add the results to the adapter to be displayed
		        	for(ParseObject a : dishList)
		        	{
		        		dishAdapter.add(new Dish(a));
		        	}
		        } else {
		        	e.printStackTrace();
		        }
		    }
		});
	}
}
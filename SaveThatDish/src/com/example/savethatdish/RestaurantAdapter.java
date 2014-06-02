package com.example.savethatdish;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class RestaurantAdapter extends ArrayAdapter<Restaurant>
{
	private Context mContext;
	private int mLayoutResourceId;
	private ImageView imageView;
	private TextView nameView, addressView; //, reviewsView, ratingView;

	public RestaurantAdapter(Context context, int layoutResourceId) {
		super(context, layoutResourceId);

		mContext = context;
		mLayoutResourceId = layoutResourceId;
	}	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        
        Restaurant currentRestaurant = getItem(position);
        if (rowView == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			rowView = inflater.inflate(mLayoutResourceId, parent, false);
		}

        rowView.setTag(currentRestaurant);
       
        imageView = (ImageView) rowView.findViewById(R.id.label_logo);
        nameView = (TextView) rowView.findViewById(R.id.label_name);
        addressView = (TextView) rowView.findViewById(R.id.label_address);
        //reviewsView = (TextView) rowView.findViewById(R.id.label_reviews);
        //milesView = (TextView) rowView.findViewById(R.id.label_mile);
        //friendView = (TextView) rowView.findViewById(R.id.label_friend);
        //ratingView = (TextView) rowView.findViewById(R.id.label_rating);
        
        nameView.setText(currentRestaurant.getName());
        addressView.setText(currentRestaurant.getAddress());
        //reviewsView.setText(currentRestaurant.getNumReviews());
        //ratingView.setText(currentRestaurant.getRating());
        
        new ImageTask().execute(currentRestaurant);

        return rowView;
	}
	
	public class ImageTask extends AsyncTask<Restaurant, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(Restaurant... arg0) {
			Bitmap mIcon = null;
			InputStream newurl;
			try {
				newurl = (InputStream) new URL(arg0[0].getImageURL()).openStream();
				mIcon = BitmapFactory.decodeStream(newurl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mIcon;
		}
		
		protected void onPostExecute(Bitmap result) {
			imageView.setImageBitmap(result);
		}
	}
}
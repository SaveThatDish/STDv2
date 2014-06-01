package com.example.savethatdish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class HamburgerActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hamburger);
		
		
		/* DishList */
        ImageView dishlist = (ImageView) findViewById(R.id.dishList);
        dishlist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	startActivity(new Intent(HamburgerActivity.this, MainActivity.class));
        }
        });
        
        /* History */
        ImageView history = (ImageView) findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	startActivity(new Intent(HamburgerActivity.this, MainActivity.class));
        }
        });
		
		
		/* Profile */
        ImageView profile = (ImageView) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	startActivity(new Intent(HamburgerActivity.this, MainActivity.class));//CHANGE THIS TO PROFILE
        }
        });
        
        
		/* Friends */
        ImageView friends = (ImageView) findViewById(R.id.friends);
        friends.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	startActivity(new Intent(HamburgerActivity.this, MainActivity.class));//CHANGE THIS TO FRIENDS
        }
        });
        
        
		/* Settings */
        ImageView settings = (ImageView) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	startActivity(new Intent(HamburgerActivity.this, SettingsActivity.class));
        }
        });
        
        
		/* Logout */
        ImageView logout = (ImageView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	startActivity(new Intent(HamburgerActivity.this, MainActivity.class));//CHANGE THIS TO LOGOUT
        }
        });
        
        
        //BACK ARROW SHENANIGANS	
	}	
}

package com.example.savethatdish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends Activity{
    float x1,x2;
    float y1, y2;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		/* CODE FOR A HAMBURGER CLICK */
        ImageButton hb = (ImageButton) findViewById(R.id.hamburgerButton);
        hb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	startActivity(new Intent(SettingsActivity.this, HamburgerActivity.class));
        }
        });
	}
	
	 /* SWIPE YOUR FINGER LEFT TO RIGHT AND HAMBURGER MENU WILL OPEN */
	 public boolean onTouchEvent(MotionEvent touchevent) 
     {
       switch (touchevent.getAction())
       {
         case MotionEvent.ACTION_DOWN: 
         {
           x1 = touchevent.getX();
           y1 = touchevent.getY();
           break;
         }
         case MotionEvent.ACTION_UP: 
         {
           x2 = touchevent.getX();
           y2 = touchevent.getY(); 

           if (x1 < x2)//L to R swipe 
           	startActivity(new Intent(SettingsActivity.this, HamburgerActivity.class));                            
         }
       }
       return false;
     }
}
package com.example.savethatdish;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DishListActivity extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.dishlist); //set the XML File.
	    Button hamburger = (Button) findViewById(R.id.hamburgerButton);  //hamburgerButton declared in dishlist.xml
	    Button addButton = (Button) findViewById(R.id.addButton);
        hamburger.setOnClickListener(this); //make a listener.
        addButton.setOnClickListener(this);

	}
    // Implement the OnClickListener callback
    public void onClick(View v) {
      /* Use the v parameter to figure out which button was clicked.
       * See: http://stackoverflow.com/questions/3320115/android-onclicklistener-identify-a-button for more
       */
    	
      if (v.getId() == R.id.hamburgerButton) {
        System.err.println("TEST UNTIL ACTUAL HAMBURGER IMPLEMENTATION");
      /* Pseudo-Code:
       * Switch to HamburgerActivity (This is shared among all Activities that have this button), e.g.:
       * Intent intent = new Intent(this, HamburgerActivity.class); //this parameter is needed for startActivity
       * startActivity(intent); //actually starts the activity
       * NOTE: If you want to receive some information from the activity call, do startActivityForResult and then implement 
       * a method called onActivityResult!
       * 
       * REFERENCE: http://developer.android.com/guide/components/activities.html#Lifecycle
       */
      }
      else if (v.getId() == R.id.addButton) {
       	System.err.println("TEST UNTIL ACTUAL ADD IMPLEMENTATION");
      }
       
      
      
    }
    
    /* 
     * Android LifeCycle
     * See: http://stackoverflow.com/questions/6812003/difference-between-oncreate-and-onstart
     * Also, see: http://developer.android.com/guide/components/activities.html#Lifecycle
     */
    
    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }
    


}

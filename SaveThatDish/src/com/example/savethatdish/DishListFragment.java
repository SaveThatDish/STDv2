package com.example.savethatdish;

import com.facebook.Session;
import com.parse.ParseUser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class DishListFragment extends Fragment implements OnClickListener {
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.w("TEST", "DishFragment onCreateView()");
		return inflater.inflate(R.layout.dishlist, container, false);
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onStart() {
	    super.onStart();
	    Log.w("TEST", "DishFragment onStart()");
	    ImageButton hamburger = (ImageButton) getView().findViewById(R.id.hamburgerButton);  //hamburgerButton declared in dishlist.xml
	    ImageButton addButton = (ImageButton) getView().findViewById(R.id.addButton);
        hamburger.setOnClickListener(this); //make a listener.
        addButton.setOnClickListener(this);

	}
	
    // Implement the OnClickListener callback
    public void onClick(View v) {
      /* Use the v parameter to figure out which button was clicked.
       * See: http://stackoverflow.com/questions/3320115/android-onclicklistener-identify-a-button for more
       */
    	
      if (v.getId() == R.id.hamburgerButton) {
    	Log.w("TEST", "Hamburger button pressed");
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
    	Log.w("TEST", "Add button pressed");
       	System.err.println("TEST UNTIL ACTUAL ADD IMPLEMENTATION");
       	
        // Log the user out. ----------------------TEMPORARY FOR TESTING-------------------------
        ParseUser.logOut();
        Session session = Session.getActiveSession();
        if (session != null){
             session.closeAndClearTokenInformation();
             session = null;
             Log.w("TEST", "logged out and cleared session");
        }
        // Go to the login view
        ((MainActivity)getActivity()).showFragment(MainActivity.SPLASH, false);
        // --------------------------------------------------------------------------------------
      }
       
      
      
    }
	
    
    /* 
     * Android LifeCycle
     * See: http://stackoverflow.com/questions/6812003/difference-between-oncreate-and-onstart
     * Also, see: http://developer.android.com/guide/components/activities.html#Lifecycle
     */
    
    @Override
	public void onStop() {
		super.onStop();
		Log.w("TEST", "DishFragment onStop()");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.w("TEST", "DishFragment onPause()");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.w("TEST", "DishFragment onResume()");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.w("TEST", "DishFragment onDestroyView()");
	}

}

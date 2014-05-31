package com.example.savethatdish;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.facebook.Session;
import com.parse.ParseUser;

public class DishListFragment extends Fragment implements OnClickListener {
		
	private boolean isCurrentlyInDishlist; //flag that indicates whether this is currently in dishlist (false if history)
	
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
	    /* Set up button, listeners */
	    ImageButton hamburger = (ImageButton) getView().findViewById(R.id.hamburgerbutton);  //hamburgerButton declared in dishlist.xml
	    ImageButton addButton = (ImageButton) getView().findViewById(R.id.addbutton);
	    ImageButton sortButton = (ImageButton) getView().findViewById(R.id.sortButton);
	    ImageButton mapButton = (ImageButton) getView().findViewById(R.id.mapButton);
	    ImageButton dishListTextButton = (ImageButton) getView().findViewById(R.id.dishListText);
	    ImageButton historyTextButton = (ImageButton) getView().findViewById(R.id.historyText);
	    
	    if (hamburger != null)
	    	hamburger.setOnClickListener(this); //make a listener.
	    if (addButton != null)
	    	addButton.setOnClickListener(this);
	    if (sortButton != null)
	    	sortButton.setOnClickListener(this);
	    if (mapButton != null)
	    	mapButton.setOnClickListener(this);
	    if (dishListTextButton != null)
	    	dishListTextButton.setOnClickListener(this);
	    if (historyTextButton != null)
	    	historyTextButton.setOnClickListener(this);

        
        isCurrentlyInDishlist = true; //start off in dishList mode.

	}
	
    // Implement the OnClickListener callback
    public void onClick(View v) {
      /* Use the v parameter to figure out which button was clicked.
       * See: http://stackoverflow.com/questions/3320115/android-onclicklistener-identify-a-button for more
       */
    	
      if (v.getId() == R.id.hamburgerbutton) {
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
      else if (v.getId() == R.id.addbutton) {
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
      
      else if (v.getId() == R.id.sortButton) {
    	  Log.w("TEST", "Sort Button pressed");
      }
      
      else if (v.getId() == R.id.mapButton) {
    	  Log.w("TEST", "Map button pressed");
      }
      
      else if (v.getId() == R.id.dishListText) {
    	  Log.w("TEST", "Dishlist button pressed");
    	  changeToDishlist();
      }
      
      else if (v.getId() == R.id.historyText) {
    	  Log.w("TEST", "History button pressed");
    	  changeToHistory();
      }
    
      
    }
	
    /*
     * Change the stuff on the screen to go from History to Dishlist
     * Parameters: None
     * Return: Boolean that indicates whether it successfully is in Dishlist now
     * Side Effects: Changes the screen UI to reflect the new mode it is in; shows the dishlist entries
     */
    
    private boolean changeToDishlist() {
    	if (isCurrentlyInDishlist)
    		return true; //already in dishlist, don't have to change anything so don't requery
    	
    	/* CHANGE THE UI */
    	
    	//change the header
    	ImageView headerText = (ImageView) getView().findViewById(R.id.headerText);
    	if (headerText != null)
    		headerText.setImageResource(R.drawable.header_hometext);
    	
    	//change the two rounded rectangles
    	ImageView leftRect = (ImageView) getView().findViewById(R.id.leftRoundedRect);
    	if (leftRect != null)
    		leftRect.setVisibility(View.VISIBLE);
    	
    	ImageView rightRect = (ImageView) getView().findViewById(R.id.rightRoundedRect);
    	if (rightRect != null)
    		rightRect.setVisibility(View.INVISIBLE);
    	
    	//set the button's texts (History and DishList) appropriately
    	ImageButton dishlistButton = (ImageButton) getView().findViewById(R.id.dishListText);
    	if (dishlistButton != null)
    		dishlistButton.setBackgroundResource(R.drawable.whitedishlisttext);
    	
    	ImageButton historyButton = (ImageButton) getView().findViewById(R.id.historyText);
    	if (historyButton != null)
    		historyButton.setBackgroundResource(R.drawable.tealhistorytext);    	
    	
    	
    	/* END CHANGING THE UI */
    	
    	
    	/* CHANGE THE LIST OF RESTAURANTS */
    	
    	/* END CHANGING THE LIST OF RESTAURANTS */
    	
    	
    	isCurrentlyInDishlist = true;
    	return true;
    	
    }
    
    /*
     * Change the stuff on the screen to go from Dishlist to History 
     * Parameters: None
     * Return: Boolean that indicates whether it is successfully in History
     * Side Effects: Changes the screen UI to reflect the new mode it is in; shows the History entries
     */
    
    private boolean changeToHistory() {
    	if (!isCurrentlyInDishlist)
    		return true; //already in history, don't have to change anything.
    	
    	/* CHANGE THE UI */
    	
    	//change the header
    	ImageView headerText = (ImageView) getView().findViewById(R.id.headerText);
    	if (headerText != null)
    		headerText.setImageResource(R.drawable.header_historytext);
    	
    	//change the two rounded rectangles
    	ImageView leftRect = (ImageView) getView().findViewById(R.id.leftRoundedRect);
    	if (leftRect != null)
    		leftRect.setVisibility(View.INVISIBLE);
    	ImageView rightRect = (ImageView) getView().findViewById(R.id.rightRoundedRect);
    	if (rightRect != null)
    		rightRect.setVisibility(View.VISIBLE);
    	
    	//set the button's texts (History and DishList) appropriately
    	ImageButton dishlistButton = (ImageButton) getView().findViewById(R.id.dishListText);
    	if (dishlistButton != null)
    		dishlistButton.setBackgroundResource(R.drawable.tealdishlisttext);
    	
    	ImageButton historyButton = (ImageButton) getView().findViewById(R.id.historyText);
    	if (historyButton != null)
    		historyButton.setBackgroundResource(R.drawable.whitehistorytext);    	

    	/* END CHANGING THE UI */
    	
    	
    	/* CHANGE THE LIST OF RESTAURANTS */
    	
    	/* END CHANGING THE LIST OF RESTAURANTS */
    	
    	
    	isCurrentlyInDishlist = false;
    	return true;
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

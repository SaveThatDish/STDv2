package com.example.test;

import android.test.ActivityUnitTestCase;

import com.example.savethatdish.ResultsActivity;
import com.example.savethatdish.SearchActivity;
import com.robotium.solo.Solo;

//public class MainTest extends AndroidTestCase {
//
//	protected void setUp() throws Exception {
//		super.setUp();
//	}
//	
//	public void testMain() {
//		
//	}
//
//}

public class SearchTest extends ActivityUnitTestCase<SearchActivity> {

	public SearchTest() {
		super(SearchActivity.class);
	}
	
	 private Solo solo;
	
	 public void setUp() throws Exception {
		 solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  public void testBurritoSearch() throws Exception {
		  //solo.waitForActivity("SearchActivity");
//		  solo.assertCurrentActivity("wrong activity", MainActivity.class);
//		  solo.clickOnButton(com.example.savethatdish.R.id.login_button);
		  solo.enterText(0, "burrito");
		  solo.enterText(1, "la jolla");
		  solo.clickOnImageButton(0);
		  solo.assertCurrentActivity("", ResultsActivity.class);
	  }
	  
	  @Override
	  public void tearDown() throws Exception {
	    solo.finishOpenedActivities();
	  }


}

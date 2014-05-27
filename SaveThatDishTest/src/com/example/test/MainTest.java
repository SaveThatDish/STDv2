package com.example.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.savethatdish.MainActivity;
import com.facebook.LoginActivity;
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

public class MainTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public MainTest() {
		super(MainActivity.class);
	}
	
//	private MainActivity activity;
//	private int buttonId;
//	
//@Override
//	protected void setUp() throws Exception {
//		super.setUp();
//		Intent intent = new Intent(getInstrumentation().getTargetContext(),
//		MainActivity.class);
//		startActivity(intent, null, null);
//		activity = getActivity();
//	}
//	
//	public void testLayout() {
//		assertNotNull(activity.findViewById(com.example.savethatdish.R.id.login_button));
//		assertNotNull(activity.findViewById(com.example.savethatdish.R.id.splash_name));
//
//	}
	
	 private Solo solo;
	
	 public void setUp() throws Exception {
		 solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  public void testLogin() throws Exception {
		  //assert(solo.waitForActivity("MainActivity"));
//		  solo.assertCurrentActivity("wrong activity", MainActivity.class);
//		  solo.clickOnButton(com.example.savethatdish.R.id.login_button);
//		  solo.clickOnView(com.example.savethatdish.R.id.)(com.example.savethatdish.R.id.action_settings);
		  solo.clickOnButton(0);
		  Thread.sleep(5000);
		  solo.assertCurrentActivity("", LoginActivity.class);
	  }
	  
	  @Override
	  public void tearDown() throws Exception {
	    solo.finishOpenedActivities();
	  }


}

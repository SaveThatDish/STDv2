package com.example.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

import com.example.savethatdish.MainActivity;

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

public class MainTest extends ActivityUnitTestCase<MainActivity> {

	public MainTest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}
	
//	protected void setUp() throws Exception {
//		super.setUp();
//	}
//	
//	public void test() {
//		fail("Not yet implemented");
//	}
	private MainActivity activity;
	
@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
		MainActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}
	
	public void testLayout() {
//		buttonId = com.vogella.android.test.simpleactivity.R.id.button1;
//		assertNotNull(activity.findViewById(buttonId));
//		Button view = (Button) activity.findViewById(buttonId);
//		assertEquals("Incorrect label of the button", "Start", view.getText());
	}


}

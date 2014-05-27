package com.example.savethatdish;

import java.util.Arrays;
import java.util.List;

import com.facebook.Request;
import com.facebook.Session;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.parse.LogInCallback;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.ParseException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class SplashFragment extends Fragment {
	
	private ImageButton loginButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, Bundle savedInstanceState) {
		Log.w("TEST", "SplashFragment onCreateView()");
	    View view = inflater.inflate(R.layout.splash, 
	            container, false);
	    return view;
	}
	
	@Override
	public void onStart() {
	    super.onStart();
	    Log.w("TEST", "SplashFragment onStart()");
	    loginButton = (ImageButton) getView().findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		onLoginButtonClicked();
        	}
        });
        
	}
	
	private void makeUserRequest() {
		Log.w("TEST", "SplashFragment making user request");
		Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
	            new Request.GraphUserCallback() {
	                @Override
	                public void onCompleted(GraphUser user, Response response) {
	                    // handle response
	                	if (user != null) {
	                       Log.w("TEST", "Setting user parseobject stuff");
	                       // Now add the data to the UI elements
	                       // Test add user email to parse
	                       ParseUser pUser = ParseUser.getCurrentUser();
	                       pUser.put("email", user.getProperty("email"));
	                       pUser.put("username", user.getName());
	                       pUser.put("fbId", user.getId());
	                       try {
	                          pUser.save();
						   } catch (ParseException e) {
				     	      Log.w("TEST", "Coulddn't save ParseUser (1)");
						   }
	                    } else if (response.getError() != null) {
	                        // handle error
	                    	Log.w("TEST", "Some error with response");
	                    }            
	                }
	            });
	    request.executeAsync();
	}
	
	private void makeFriendsRequest() {
		Log.w("TEST", "SplashFragment making friends request");
		Request request = Request.newMyFriendsRequest(ParseFacebookUtils.getSession(),
				new Request.GraphUserListCallback() {
					@Override
					public void onCompleted(List<GraphUser> friends, Response response) {
						if (friends != null && friends.size() > 0) {
							Log.w("TEST", "Setting user friends");
							String[] friendIds = new String[friends.size()];
							GraphUser next;
							for (int i=0; i<friends.size(); i++) {
								next = friends.get(i);
								friendIds[i] = next.getId();
							}
							ParseUser pUser = ParseUser.getCurrentUser();
						    pUser.addAllUnique("friends", Arrays.asList(friendIds));
							pUser.saveInBackground();
						} else if (friends != null && friends.size() == 0) {
							Log.w("TEST", "No friends are using app");
						} else if (response.getError() != null) {
							Log.w("TEST", "Some error with response");
							Log.w("TEST", response.getError().getErrorMessage());
						}
					}
				});
		request.executeAsync();
	}
	
	private void onLoginButtonClicked() {
		Log.w("TEST", "Login button pressed");
    	List<String> permissions = Arrays.asList("email", "user_friends");
    	ParseFacebookUtils.logIn(permissions, getActivity(), new LogInCallback() {
    		  @Override
    		  public void done(ParseUser user, ParseException err) {
    		    if (user == null) {
    		      Log.d("TEST", "Uh oh. The user cancelled the Facebook login.");
    		    } else if (user.isNew()) {
    		      Log.d("TEST", "User signed up and logged in through Facebook!");
    		      // Fetch Facebook user info if the session is active
    		        Session session = ParseFacebookUtils.getSession();
    		        if (session != null && session.isOpened()) {
    		        	Log.w("TEST", "Session is open, therefore open dishlsit");
    		        	((MainActivity)getActivity()).showFragment(MainActivity.DISHLIST, false);
    		        	makeUserRequest();
    		        	makeFriendsRequest();
    		        }
    		        if(ParseFacebookUtils.isLinked(user)) {
	    		    	  Log.w("TEST", "User is linked with parse");
	    		      } else {
	    		    	  Log.w("TEST", "User not linked with parse");
	    		      }

    		    } else {
    		      Log.d("TEST", "User logged in through Facebook!");
    		      //ParseFacebookUtils.saveLatestSessionData(user);
    		      // Fetch Facebook user info if the session is active
    		     
    		        Session session = ParseFacebookUtils.getSession();
    		        if (session != null && session.isOpened()) {
    		        	Log.w("TEST", "Session is open, therefore open dishlsit");
    		        	((MainActivity)getActivity()).showFragment(MainActivity.DISHLIST, false);
    		        	makeFriendsRequest();
    		        }
    		      if(ParseFacebookUtils.isLinked(user)) {
    		    	  Log.w("TEST", "User is linked with parse");
    		      } else {
    		    	  Log.w("TEST", "User not linked with parse");
    		      }
    		    }
    		  }
    		});
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.w("TEST", "SplashFragment onStop()");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.w("TEST", "SplashFragment onPause()");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.w("TEST", "SplashFragment onResume()");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.w("TEST", "SplashFragment onDestroyView()");
	}
	
}

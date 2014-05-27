package com.example.savethatdish;

import java.util.List;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;


public class Dish {

   private String name;
   private String restaurant;
   private String location;
   
   //we need to store the location data, not restaurant name. location implicitly has res. name as well
   public Dish(String dishName, String shortAddress) { //what info is the location stored as... string? 
      
	  //Assign variables
	  this.name = dishName;
      this.location = shortAddress;   
      
      //Check to see if the dish is already in the database
      ParseQuery<ParseObject> query = ParseQuery.getQuery("Dish");
      query.whereEqualTo("name", this.name);
      
      //query.whereEqualTo("short_address", shortAddress);
      query.findInBackground(new FindCallback<ParseObject>() {
          public void done(List<ParseObject> list, ParseException e) {
              if (e == null) 
              {
            	  Log.d("testing", "List is of size  " + list.size());
              } 
              else 
              {
                  Log.d("testing", "Error: " + e.getMessage());
              }
          }
      });
      
      //if dish doesn't exist in parse
        //addToParse(); //check return type/error handling. what do we do if it fails?
      //else
        //pull info from parse
      
      //this.restaurant = restaurantName; QUERY RESTAURANT NAME

      
      //should we create a ParseQuery object? We'll need it for allLikes and onBucketList only, 
      //so maybe only create the object in the methods that actually need it?
      
         }

   /*
    * Creating the dish in the constructor doesn't make sense: what if the dish exists already and we're 
    * only interested in creating a local var for the sake of convenience. Instead create a method that does that.
    * 
    * @Return: boolean to check for success? 
    * OtheR: Error handling should be done within this method.  
    */
   
   private boolean addToParse() {
	   ParseObject dish = new ParseObject("Dish"); 
	   dish.add("name", name);
	   
	   //PULL INFORMATION (LOCATION, RESTAURANT NAME...) FROM YELP
	   //LAWRENCE HELP
	   
	   dish.saveInBackground();//Adds to database
	   
	   //Associated info to add: restaurant name, location. Anything else?
	   return true; //need to do error handling before returning
   }
   
   /*
    * Purpose: delete local copy, connect to parse?
    * @Return: Boolean value indicating whether remove was successful
    * Other: Error Handling
    */
   
   public boolean removeDish() {
	   return true;
   }

   /* 
    * @param: rating
    * @return: Boolean value letting caller know if it was successful.
    * Other: Error handling with Parse should be done in this method. Use that
    * info to return the proper Boolean
    */
   public boolean rateDish(int rating) // integer or boolean param?
   {
	   return true;
   }

   /* 
    * @return: returns the number of total likes for a dish
    */
   public int allLikes() {
      return 0;
   }

   /* 
    * @param: userName
    * @return: returns true if dish is on bucketlist,
    *  	    returns false if dish is not on bucketlist
    */
   public boolean onBucketlist(String userName) {
      return false;
   }

   /* 
    * @param: userName
    * @return: returns true if successfully added to bucketlist,
    *  	    returns false if already on bucketlist
    */
   public boolean addToBucketlist(String userName) {
      if (onBucketlist(userName))
        return false;
      //else add to Parse
      return true;
   }

}

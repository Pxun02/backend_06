package com.example.sc2006;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/** An object to manage MyFavourite. */
public class MyFavouriteMgr extends Utility{
    /** Empty constructor. */
    MyFavouriteMgr(){}

    public MyFavourite[] fbRead(String user_id){
        DatabaseReference mDatabase = FirebaseDatabase
            .getInstance("https://carpark-milkshake-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference();
        Query mQueryRef = mDatabase.child("MyFavourite");
        MyFavourite mFPtr[] = new MyFavourite[i];

        mQueryRef.addChildEventListener(new ChildEventListner(){
            @Override
            public void onChildAdded(DataSnapshot snapshot){
                int index = 0;
                for(int j=0;j<i;j++){
                    String fetched_user_id = snapshot.child(IntegertoString(j).getValue("user_id"));
                    if(fetched_user_id.equals(user_id))
                        mFPtr[index++] = snapshot.child(Integer.toString(j)).getValue(MyFavourite.class);
                }
            }
        });
        return mFPtr;
    }

    //Will be updated!
    /**
     * A method to show contents stored in MyFavourite table.
     * @param user_id an user_id of a user to check his/her favourite carparks
     * @returns 2d-array of query: { {favourite_id, carpark_id} } for a selected user
     */
    /*
    public ArrayList<ArrayList<Integer>> showMyFavourite(int user_id){
        MyFavourite myFavourite = new MyFavourite();
        ArrayList<ArrayList<Integer>> favourite_arrs = new ArrayList<ArrayList<Integer>>(); 
        //json reads from firebase realtime database
        
        JSONOArray j_myFavourite_arr = fbRead("MyFavourite");
        for(int i=0;i<j_myFavourite_arr.length();i++){
            JSONObject j_myFavourite = j_myFavourite_arr.getJSONObject(i);

            Integer favourite_id = j_myFavourite.getInteger("favourite_id");
            Integer carpark_id = j_myFavourite.getInteger("carpark_id");
            ArrayList<Integer> favourite_arr = {favourite_id, carpark_id};
            favourite_arrs.add(Favourite_arr);
        }
        
        return favourite_arrs;
    }

     */
}

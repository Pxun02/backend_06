package com.example.sc2006;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/** An object to manage MyHistory. */
public class MyHistoryMgr extends Utility{
    /** Empty constructor. */
    MyHistoryMgr(){}

    public MyHistory[] fbRead(String user_id){
        DatabaseReference mDatabase = FirebaseDatabase
            .getInstance("https://carpark-milkshake-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference();
        Query mQueryRef = mDatabase.child("MyHistory");
        MyHistory mHPtr[] = new MyHistory[i];

        mQueryRef.addChildEventListener(new ChildEventListner(){
            @Override
            public void onChildAdded(DataSnapshot snapshot){
                int index = 0;
                for(int j=0;j<i;j++){
                    String fetched_user_id = snapshot.child(IntegertoString(j).getValue("user_id"));
                    if(fetched_user_id.equals(user_id))
                        mHPtr[index++] = snapshot.child(Integer.toString(j)).getValue(MyHistory.class);
                }
            }
        });
        return mHPtr;
    }

    //Will be updated
    /**
     * A method to show contents stored in MyHistory table.
     * @param user_id an user_id of a user to check his/her history
     * @returns 2d-array of query: { {history_id, carpark_id} } for a selected user
     */
    public ArrayList<ArrayList<Integer>> showMyHistory(int user_id){
        ArrayList<ArrayList<Integer>> history_arrs = new ArrayList<ArrayList<Integer>>(); 
        //json reads from firebase realtime database
        
        MyHistory[] myHistory = (MyHistory) Utility.fbRead("MyHistory", i);
        for(int i=0;i<j_myHistory_arr.length();i++){
            JSONObject j_myHistory = j_myHistory_arr.getJSONObject(i);

            Integer history_id = j_myHistory.getInteger("history_id");
            Integer carpark_id = j_myHistory.getInteger("carpark_id");
            ArrayList<Integer> history_arr = {history_id, carpark_id};
            history_arrs.add(history_arr);
        }
        
        return history_arrs;
    }

}

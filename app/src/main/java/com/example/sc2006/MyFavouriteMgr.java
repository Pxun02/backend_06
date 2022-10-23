package com.example.sc2006;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class MyFavouriteMgr extends DialogFragment {
    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void showMyFavourite(int user_id){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance("https://carpark-milkshake-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference();
        Query mQueryRef = mDatabase.child("MyFavourite");

        mQueryRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> favouriteList = new ArrayList<>();
                for(DataSnapshot postsnapshot: snapshot.getChildren()) {
                    MyFavourite fetched_myfavourite = postsnapshot.getValue(MyFavourite.class);
                    if(fetched_myfavourite.user_id.equals(user_id)) {
                        favouriteList.add(fetched_myfavourite.carpark_id);
                    }
                }
                String title = "Favourite List";
                ListDialog list_d = new ListDialog(title, favouriteList.toArray(new String[0]));
                list_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                error.toException();
            }
        });
    }
}

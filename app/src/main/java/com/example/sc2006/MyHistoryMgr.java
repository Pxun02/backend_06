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

public class MyHistoryMgr extends DialogFragment {
    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void showMyHistory(int user_id){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance("https://carpark-milkshake-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference();
        Query mQueryRef = mDatabase.child("MyHistory");

        mQueryRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> historyList = new ArrayList<>();
                for(DataSnapshot postsnapshot: snapshot.getChildren()) {
                    MyHistory fetched_myhistory = postsnapshot.getValue(MyHistory.class);
                    if(fetched_myhistory.user_id.equals(user_id)) {
                        historyList.add(fetched_myhistory.carpark_id);
                    }
                }
                String title = "Carpark History";
                ListDialog list_d = new ListDialog(title, historyList.toArray(new String[0]));
                list_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                error.toException();
            }
        });
    }
}

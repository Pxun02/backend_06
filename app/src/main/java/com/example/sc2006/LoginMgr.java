package com.example.sc2006;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

public class LoginMgr extends DialogFragment {
    private void LoginFB(String email, String password){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                        }
                    }
                });
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public int verifyCredentials(String email, String password){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance("https://carpark-milkshake-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference();
        Query mQueryRef = mDatabase.child("MyAccount");

        mQueryRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                error.toException();
            }

            @Override
            public void onDataChange(DataSnapshot snapshot){
                MyAccount myaccountPtr[] = new MyAccount[1];
                for(DataSnapshot postDatasnapshot : snapshot.getChildren()){
                    if(postDatasnapshot.child("email").getValue() == email) {
                        myaccountPtr[0] = postDatasnapshot.getValue(MyAccount.class);
                    }
                }
                if(myaccountPtr[0] == null) {
                    String message = "No matched accounts found. Please sign up first.";
                    SimpleDialog sin_d = new SimpleDialog(message);
                    sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                    return;
                }
                if(myaccountPtr[0].email != email){
                    String message = "Invalid email. Please input the valid email.";
                    SimpleDialog sin_d = new SimpleDialog(message);
                    sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                    return; //email mismatched
                }
                if(myaccountPtr[0].password != password){
                    String message = "Invalid password. Please input the valid password.";
                    SimpleDialog sin_d = new SimpleDialog(message);
                    sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                    return; //password mismatched
                }
            }
        });
        //login to firebase
        LoginFB(email, password);

        return 1; //success!
    }
}

package com.example.sc2006;
import android.app.Dialog;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

public class RegisterAccountMgr extends DialogFragment {
    private void fbWrite(MyAccount myaccount){
        Map<String, MyAccount> myaccount_map = new HashMap<>();
        myaccount_map.put(myaccount.user_id, myaccount);
        String URL = "https://carpark-milkshake-default-rtdb.asia-southeast1.firebasedatabase.app/";
        DatabaseReference mDatabase = FirebaseDatabase.getInstance(URL).getReference("MyAccount");
        mDatabase.setValue(myaccount_map);
    }

    private int verifyEmail(String email){
        if(email.compareTo("")!=0) return -2; //email is empty
        if(email.indexOf('@')==-1)  return -1; //no @ is contained
        return 1;//success
    }

    private int validatePassword(String password){
        if(password.length()<7) return -4;
        if(password.toLowerCase().compareTo(password)!=0)  return -3;
        if(Pattern.compile("[0-9]").matcher(password).find())   return -2;
        char[] charList = {'.','_','-'};
        int symbols = 0;
        for(int i=0;i<2;i++){
            if(password.indexOf(charList[i])!=-1){
                symbols++;
                break;
            }
        }
        if(symbols==0)  return -1;
        return 1;
    }

    private void authentication(String email) {
        ActionCodeSettings actionCodeSettings =
            ActionCodeSettings.newBuilder()
                // URL you want to redirect back to. The domain (www.example.com) for this
                // URL must be whitelisted in the Firebase Console.
                .setUrl("https://www.example.com/finishSignUp?cartId=1234")
                // This must be true
                .setHandleCodeInApp(true)
                .setIOSBundleId("com.example.ios")
                .setAndroidPackageName(
                        "com.example.android",
                        true, /* installIfNotAvailable */
                        "12"    /* minimumVersion */)
                .build();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendSignInLinkToEmail(email, actionCodeSettings)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    String message = "An authentication email is sent.";
                    SimpleDialog sin_d = new SimpleDialog(message);
                    sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                }
            });
        Intent intent = getActivity().getIntent();
        String emailLink = intent.getData().toString();

        // Confirm the link is a sign-in with email link.
        if (auth.isSignInWithEmailLink(emailLink)) {

            // The client SDK will parse the code from the link for you.
            auth.signInWithEmailLink(email, emailLink)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String message = "Your email is authenticated!";
                        SimpleDialog sin_d = new SimpleDialog(message);
                        sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                    }
                });
        }
    }

    private void createFBAccount(String email, String password){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
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
    public void AccountCreation(String user_id, String name, String email, String first_name, String last_name, String password){
        //verify email
        switch(verifyEmail(email)){
            case(-2):
                String message = "Empty email! Please input email.";
                SimpleDialog sin_d = new SimpleDialog(message);
                sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                return;
            case(-1):
                message = "Invalid email.";
                sin_d = new SimpleDialog(message);
                sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                return;
        }

        //validate password
        switch(validatePassword(password)){
            case(-4):
                String message = "Password has to be at least 8 letters.";
                SimpleDialog sin_d = new SimpleDialog(message);
                sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                return;
            case(-3):
                message = "Password has to include both lower and capital letters.";
                sin_d = new SimpleDialog(message);
                sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                return;
            case(-2):
                message = "Password has to include at least one digit.";
                sin_d = new SimpleDialog(message);
                sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                return;
            case(-1):
                message = "Password has to include at least one of following symbols: ['.', '_', '-'].";
                sin_d = new SimpleDialog(message);
                sin_d.show(getActivity().getSupportFragmentManager(), "my_dialog");
                return;
        }

        //refer to database, and check whether email and user_id are unique
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance("https://carpark-milkshake-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference();
        Query mQueryRef = mDatabase.child("MyAccount");

        mQueryRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot snapshot){
                boolean unique_user_id = true;
                boolean unique_email = true;
                for(DataSnapshot postDatasnapshot: snapshot.getChildren()){
                    if(postDatasnapshot.getKey().equals(user_id))
                        unique_user_id = false;
                    if(postDatasnapshot.child("email").getValue().equals(email))
                        unique_email = false;
                }
                if(unique_user_id && unique_email) {
                    //register account
                    MyAccount myaccount = new MyAccount(user_id, name, email, first_name, last_name, password);
                    fbWrite(myaccount);
                    authentication(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                error.toException();
            }
        });

        //create new user profile on firebase-auth
        createFBAccount(email, password);
    }
}

package com.example.sc2006;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterAccountMgr ram = new RegisterAccountMgr();
        String user_id = "19201";
        String name = "Jacky";
        String email = "ntunus@smu.com";
        String first_name = "Jack";
        String last_name = "Wong";
        String password = "2Wsoa_sjd";
        ram.AccountCreation(user_id, name, email, first_name, last_name, password);
    }
}
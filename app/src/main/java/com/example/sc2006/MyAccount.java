package com.example.sc2006;

public class MyAccount {
    
    public String user_id;
    public String name;
    public String email;
    public String first_name;
    public String last_name;
    public String password;

    public MyAccount() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public MyAccount(String user_id, String name, String email, String first_name, String last_name, String password) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
    }

}

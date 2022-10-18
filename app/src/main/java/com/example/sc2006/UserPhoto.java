package com.example.sc2006;

public class UserPhoto {
    public int photo_id;
    public int user_id;
    public Object photo;

    public UserPhoto(){}

    public UserPhoto(int photo_id, int user_id, Object photo){
        this.photo_id = photo_id;
        this.user_id = user_id;
        this.photo = photo;
    }
}

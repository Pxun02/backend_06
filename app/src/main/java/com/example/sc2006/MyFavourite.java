package com.example.sc2006;

import java.time.LocalDateTime;
public class MyFavourite {
    public int favourite_id;
    public int user_id;
    public int carpark_id;
    public LocalDateTime created_at;

    public MyFavourite(){}

    public MyFavourite(int favourite_id, int user_id, int carpark_id, LocalDateTime created_at){
        this.favourite_id = favourite_id;
        this.user_id = user_id;
        this.carpark_id = carpark_id;
        this.created_at = created_at;
    }
}

package com.example.sc2006;

import java.time.LocalDateTime;
public class MyHistory {
    public String history_id;
    public String user_id;
    public String carpark_id;
    public LocalDateTime created_at;

    public MyHistory(){}

    public MyHistory(String history_id, String user_id, String carpark_id, LocalDateTime created_at){
        this.history_id = history_id;
        this.user_id = user_id;
        this.carpark_id = carpark_id;
        this.created_at = created_at;
    }
}

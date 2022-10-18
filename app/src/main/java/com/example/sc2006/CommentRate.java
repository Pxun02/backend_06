package com.example.sc2006;

public class CommentRate {
    public int comment_id;
    public int rate_id;
    public int carpark_id;
    public int user_id;
    public String comment;
    public int rate;

    public CommentRate(){}

    public CommentRate(int comment_id, int rate_id, int carpark_id, int user_id, String comment, int rate){
        this.comment_id = comment_id;
        this.rate_id = rate_id;
        this.carpark_id = carpark_id;
        this.user_id = user_id;
        this.comment = comment;
        this.rate = rate;
    }

}

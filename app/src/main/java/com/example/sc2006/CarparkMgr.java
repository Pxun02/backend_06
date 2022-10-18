package com.example.sc2006;

public class CarparkMgr {
    /*
    time: URACarparkAPI.startTime, .endTime
    fee: URACarparkAPI.weekdayRate, .SatdayRate, .SunPHRate
    destination coordinates: GoogleMapsAPI.
    near carparks: GoogleMapsAPI.getObj("rows").getArray(i)<order: distance> Where Open(carpark.name) >= startTime && Open(~) <= endTime
    crowdness: CarparkAPI.items_arr.get(i).carpark_data_arr.get(j).carpark_info_arr.get(i).lots_available
        Where CarparkAPI.items_arr.get(i).carpark_data_arr.get(j).carpark_number matched to carparkName
    userRate: DB.getArray(CommentRate).getObj(i).rate
        Where DB.getArray(CommentRate).getObj(i).carpark_id matched to carparkName
    */
    public void masterManager(){
        /*
        1 from URA, get opening time
        2 from URA, according to the current day, get the fee
        3 get the destination matrix, from user input of destination and GoogleMaps Distance Matrix
        4 get the near carparks, except those closing from 1, 3
        5 from gov API, get crowdness
        6 order data: distance (3, 4), fee(2), userRate(RealtimeDB->CommentRate), crowd(5)
        */
    }
}

package com.example.sc2006;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.net.*;

import org.apache.commons.io.IOUtils;
import org.json.*;

public class CarparkAPIMgr {
    public CarparkAPIMgr(){}

    public CarparkAPI fetchCarparkAPI() throws IOException{
        String rawURL = "https://api.data.gov.sg/v1/transport/carpark-availability";
        LocalDateTime rawDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String strDateTime = rawDateTime.format(dateFormat).replace(":", "%3A");
        String strUrl = rawURL + "?date_time=" + strDateTime;

        ArrayList<CARPARK_DATA> list_cda = new ArrayList<>();

        String[] timestamp = new String[1];
        CarparkAPI[] carparkAPIPtr = new CarparkAPI[1];

        try{
            JSONObject carpark_api = new JSONObject(IOUtils.toString(new URL(strUrl), Charset.forName("UTF-8")));

            JSONArray j_items_arr = carpark_api.getJSONArray("items");
            JSONObject j_items = j_items_arr.getJSONObject(0);
            timestamp[0] = j_items.getString("timestamp");
            JSONArray j_carpark_data_arr = j_items.getJSONArray("carpark_data");
            for(int i=0;i<j_carpark_data_arr.length();i++){
                JSONObject j_carpark_data = j_carpark_data_arr.getJSONObject(i);
                String carpark_number = j_carpark_data.getString("carpark_number");

                JSONArray j_carpark_info_arr = j_carpark_data.getJSONArray("carpark_info");
                JSONObject j_carpark_info = j_carpark_info_arr.getJSONObject(0);

                String total_lots = j_carpark_info.getString("total_lots");
                String lot_type = j_carpark_info.getString("lot_type");
                String lots_available = j_carpark_info.getString("lots_available");

                CARPARK_DATA cda = new CARPARK_DATA(carpark_number, total_lots, lot_type, lots_available);
                list_cda.add(cda);
            }
            
            CarparkAPI carparkAPI_temp = new CarparkAPI(timestamp[0], list_cda);
            carparkAPIPtr[0] = carparkAPI_temp;

        }catch(IOException | JSONException e){
            e.printStackTrace();
        }

        CarparkAPI carparkAPI = carparkAPIPtr[0];
        return carparkAPI;
    }
}

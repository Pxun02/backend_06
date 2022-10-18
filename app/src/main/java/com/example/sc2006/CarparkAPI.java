package com.example.sc2006;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/* CarparkAPI 
{
    timestamp,
    CARPARK_DATA{
        carpark_number,
        total_lots,
        lot_type,
        lots_available,
    }
}
*/
/** struct-like class for CarparkAPI */
class CarparkAPI {
    String timestamp;
    ArrayList<CARPARK_DATA> carpark_data_arr;

    CarparkAPI(){}

    CarparkAPI(String timestamp, ArrayList<CARPARK_DATA> carpark_data_arr){
        this.timestamp = timestamp;
        this.carpark_data_arr = carpark_data_arr;
    }
}

/** struct-like class for CARPARK_DATA_ */
class CARPARK_DATA {
    String carpark_number;
    String total_lots;
    String lot_type;
    String lots_available;

    CARPARK_DATA(){}

    CARPARK_DATA(String carpark_number, String total_lots, String lot_type, String lots_available){
        this.carpark_number = carpark_number;
        this.total_lots = total_lots;
        this.lot_type = lot_type;
        this.lots_available = lots_available;
    }
}

package com.example.sc2006;

public class URACarparkAPI {
    public String ppName;
    public String ppCode;
    public String startTime;
    public String endTime;
    public String weekdayRate;
    public String weekdayMin;
    public String satdayRate;
    public String satdayMin;
    public String sunPHRate;
    public String sunPHMin;

    public URACarparkAPI(){}

    public URACarparkAPI(String ppName, 
                        String ppCode, 
                        String startTime, 
                        String endTime, 
                        String weekdayRate, 
                        String weekdayMin, 
                        String satdayRate,
                        String satdayMin, 
                        String sunPHRate, 
                        String sunPHMin){
        this.ppName = ppName;
        this.ppCode = ppCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekdayRate = weekdayRate;
        this.weekdayMin = weekdayMin;
        this.satdayRate = satdayRate;
        this.satdayMin = satdayMin;
        this.sunPHRate = sunPHRate;
        this.sunPHMin = satdayMin;
    }
}

package com.stationerymall.traineralertsystem;

public class Alert {
    String time;
    String drugname;
    String drugnumber;
    boolean completed;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public String getDrugnumber() {
        return drugnumber;
    }

    public void setDrugnumber(String drugnumber) {
        this.drugnumber = drugnumber;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Alert(String time, String drugname, String drugnumber, boolean completed) {
        this.time = time;
        this.drugname = drugname;
        this.drugnumber = drugnumber;
        this.completed = completed;
    }


}

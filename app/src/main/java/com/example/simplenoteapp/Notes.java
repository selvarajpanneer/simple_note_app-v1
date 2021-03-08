package com.example.simplenoteapp;

public class Notes {
    int slno;
    String title;
    String content;
    String creation_time,alarmtime;
    Long last_modified_time;

    public Notes() {
    }

    public Notes(int slno,String title, String content, String creation_time,Long last_modified_time) {
        this.slno=slno;
        this.title = title;
        this.content = content;
        this.creation_time = creation_time;
        this.last_modified_time=last_modified_time;
    }
    public Notes(int slno,String title, String content, String creation_time,String alarmtime,Long last_modified_time) {
        this.slno=slno;
        this.title = title;
        this.content = content;
        this.creation_time = creation_time;
        this.alarmtime=alarmtime;
        this.last_modified_time=last_modified_time;
    }
    public int getSlno(){return  slno;}

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public String getAlarmtime(){ return alarmtime; }

    public Long getLast_modified_time(){return last_modified_time;}
}

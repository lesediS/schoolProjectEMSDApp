package com.example.emsd_app;

import android.util.Log;

import java.util.ArrayList;

public class Event {

    public static ArrayList<Event> eventsList = new ArrayList<>();

    public static ArrayList<Event> eventsForDate(int year,int month, int day){
        ArrayList<Event> events = new ArrayList<>();

        Log.d("eventsForDate", "year : " + year + " month : " + month + " day : " + day);
        for(Event event : eventsList){
            Log.d("eventsForDate", " event year : " + event.getYear() + " month : " + event.getMonth()+ " day : " + event.getDay());
            if(event.getMonth() == month && event.getYear() == year && event.getDay() == day){
                Log.d("eventsForDate", "event added");
                events.add(event);
            }
        }
        return events;

    }
    private String eventID;
    private String name;
   // private int color;
    private String startTime;
    private String endTime;
    private int year;
    private int month;
    private int day;
    private String classType;
    private String level;

    public Event(String eventID, String name, String startTime, String endTime, int year, int month, int day, String classType, String level) {
        this.eventID = eventID;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.year = year;
        this.month = month;
        this.day = day;
        this.classType = classType;
        this.level = level;
    }

    public Event() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}

package com.example.emsd_app;

import android.annotation.SuppressLint;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CalenderUtils {

    public static LocalDate selectedDate;
    public static LocalTime selectedTime;

    @SuppressLint("NewApi")
    public static String formatDate(LocalDate selectedDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return selectedDate.format(formatter);
    }
    @SuppressLint("NewApi")
    public static String formatTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");
        return time.format(String.valueOf(formatter));
    }
}

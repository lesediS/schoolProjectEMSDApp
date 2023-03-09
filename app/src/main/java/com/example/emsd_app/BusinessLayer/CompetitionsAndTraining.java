package com.example.emsd_app.BusinessLayer;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.emsd_app.DataAccess.EMDSDB;

import java.util.ArrayList;

public class CompetitionsAndTraining implements EMDSDB.UserValidation {

    public static ArrayList<String> CompetitionDates = new ArrayList<String>();
    public static ArrayList<String> TrainingDates = new ArrayList<String>();

    public ArrayList<String> GetCompetitionDates(){

        EMDSDB NewDates = new EMDSDB();
        NewDates.GetCompetitionSchedules();

        Log.d(TAG,"Working");


        return this.CompetitionDates;
    }

    public ArrayList<String> GetTrainingDates(){

        EMDSDB TrainingDates = new EMDSDB();
        TrainingDates.GetTrainingSchedules();

        Log.d(TAG,"Working");


        return this.TrainingDates;
    }


}

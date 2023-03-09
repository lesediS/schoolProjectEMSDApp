package com.example.emsd_app;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.emsd_app.BusinessLayer.CompetitionsAndTraining;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Schedule extends Fragment  {

private Button HipHopButton;
private Button ModernButton;
private Button HpButton;
private com.applandeo.materialcalendarview.CalendarView Schedule;
private Button Back;

private AlertDialog.Builder CompetitionDateDetailsBuilder;
private AlertDialog CompetitionDateDetails;
//For Demo Purpose Only
public String[] HipHopCompetition = new String[10];
public String[] ModernCompetition = new String[10];
public String[] HPCompetition = new String[10];
//For Demo Purpose Only
public String[] AllTrainingDates= new String[20];

//This programming statement was adapted from GitHub:
//Link: https://github.com/Applandeo/Material-Calendar-View
//Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
//Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
// https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
// https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
public ArrayList<EventDay> TestDates = new ArrayList<EventDay>();
//This programming statement was adapted from GitHub:
//Link: https://github.com/Applandeo/Material-Calendar-View
//Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
//Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
// https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
// https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
public ArrayList<EventDay> TrainingDates = new ArrayList<EventDay>();
//This programming statement was adapted from StackOverflow:
//Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
//Author: Devchris
//Author Profile Link: https://stackoverflow.com/users/14488059/devchris
ExecutorService ExecuteFunctions = Executors.newSingleThreadExecutor();
//This programming statement was adapted from StackOverflow:
//Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
//Author: Devchris
//Author Profile Link: https://stackoverflow.com/users/14488059/devchris
Handler HandleOutput = new Handler(Looper.getMainLooper());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ScheduleFragment =  inflater.inflate(R.layout.fragment_schedule, container, false);
        //Button Setup
        HipHopButton = ScheduleFragment.findViewById(R.id.HipHopButton);
        ModernButton =ScheduleFragment.findViewById(R.id.ModernButton);
        HpButton = ScheduleFragment.findViewById(R.id.HPButton);
        Back = ScheduleFragment.findViewById(R.id.BackButton);



        //Calander Setup (Test)
        Schedule = ScheduleFragment.findViewById(R.id.calendarView);
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
        //Author: Devchris
        //Author Profile Link: https://stackoverflow.com/users/14488059/devchris
        ExecuteFunctions.execute(() ->{

            CompetitionsAndTraining SetDates = new CompetitionsAndTraining();
            SetDates.GetCompetitionDates();
            SetDates.GetTrainingDates();

            //This programming statement was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
            //Author: Devchris
            //Author Profile Link: https://stackoverflow.com/users/14488059/devchris
            HandleOutput.post(() ->{

                if(CompetitionsAndTraining.CompetitionDates.isEmpty()){

                    Log.d(TAG, "No competition dates are available");

                }else{

                    if(CompetitionsAndTraining.CompetitionDates.size() > 1){
                        int counter =0;
                        for(String Dates: CompetitionsAndTraining.CompetitionDates){

                            counter++;
                            HipHopCompetition[counter] = Dates;
                        }

                    }
                }

                //Add Training Dates
                if(CompetitionsAndTraining.TrainingDates.isEmpty()){

                    Log.d(TAG, "No training dates are available");

                }else{

                    if(CompetitionsAndTraining.TrainingDates.size() > 1){
                        int counter =0;
                        for(String Dates: CompetitionsAndTraining.TrainingDates){

                            counter++;
                            AllTrainingDates[counter] = Dates;
                        }

                    }
                }

                DisplayTrainingDates();
                DisplayHipHopCompetitionDates();
                DisplayModernCompetitionDates();
                DisplayHPCompetitionDates();

            });

        });

        //Add Competition Dates

        //Demo Data-Back-up if pull from database fails.
       HipHopCompetition[0] = "10 November 2022";
       HipHopCompetition[1] = "11 November 2022";
       ModernCompetition[0] = "15 November 2022";
       HPCompetition[0] = "23 November 2022";

       //Demo Data-Back-up if pull from database fails.
       AllTrainingDates[0] = "03 November 2022";
       AllTrainingDates[1] = "04 November 2022";
       AllTrainingDates[2] = "05 November 2022";
       AllTrainingDates[3] = "06 November 2022";
       AllTrainingDates[4] = "07 November 2022";
       AllTrainingDates[5] = "15 November 2022";
       AllTrainingDates[6] = "17 November 2022";
       AllTrainingDates[7] = "18 November 2022";




        //This method was adapted from StackOverflow:
        //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
        //Author: Harsh Bhavsar
        //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager Manager7 = getFragmentManager();
                FragmentTransaction ReturnToCompetitionPage = Manager7.beginTransaction();
                Fragment Competition =  new Competition();
                ReturnToCompetitionPage.replace(R.id.fragment_container,Competition);
                ReturnToCompetitionPage.commit();
            }
        });

        Schedule.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(@NonNull EventDay eventDay) {

                //This programming statement was adapted from JavaTPoint:
                //Link: https://www.javatpoint.com/android-alert-dialog-example
                //Author: JavaTPoint
                CompetitionDateDetailsBuilder = new AlertDialog.Builder(getContext());
                //This programming statement was adapted from JavaTPoint:
                //Link: https://www.javatpoint.com/android-alert-dialog-example
                //Author: JavaTPoint
                CompetitionDateDetails = CompetitionDateDetailsBuilder.create();
                //This programming statement was adapted from JavaTPoint:
                //Link: https://www.javatpoint.com/android-alert-dialog-example
                //Author: JavaTPoint
                CompetitionDateDetails.setTitle("Competition Details");

                if(HipHopButton.isPressed()){
                    //This programming statement was adapted from JavaTPoint:
                    //Link: https://www.javatpoint.com/android-alert-dialog-example
                    //Author: JavaTPoint
                   CompetitionDateDetails.setMessage("Class: Hip-Hop "+"\n"+ "Judge: " + "Eunice Marais " +"\n" + "Time: 10:00-14:00" + "\n" + "Levels Participating: 1,4,6,7,8");

                }
                if(ModernButton.isPressed()){
                    //This programming statement was adapted from JavaTPoint:
                    //Link: https://www.javatpoint.com/android-alert-dialog-example
                    //Author: JavaTPoint
                   CompetitionDateDetails.setMessage("Class: Modern "+"\n"+ "Judge: " + "Eunice Marais " +"\n" + "Time: 10:00-14:00" + "\n" + "Levels Participating: 2,3,5");

                }
                if(HpButton.isPressed()){
                    //This programming statement was adapted from JavaTPoint:
                    //Link: https://www.javatpoint.com/android-alert-dialog-example
                    //Author: JavaTPoint
                   CompetitionDateDetails.setMessage("Class: HP "+"\n"+ "Judge: " + "Eunice Marais " +"\n" + "Time: 10:00-14:00" + "\n" + "Levels Participating: 1,4,8");
                }

                //This programming statement was adapted from JavaTPoint:
                //Link: https://www.javatpoint.com/android-alert-dialog-example
                //Author: JavaTPoint
                CompetitionDateDetails.setMessage("No competitions have been scheduled for today");

                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/30018587/alertdialog-setbutton-was-deprecated
                //Author: Shahzad Afridi
                //Author Profile Link: https://stackoverflow.com/users/6672577/shahzad-afridi
                CompetitionDateDetails.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CompetitionDateDetails.dismiss();
                    }
                });
                //This programming statement was adapted from JavaTPoint:
                //Link: https://www.javatpoint.com/android-alert-dialog-example
                //Author: JavaTPoint
                CompetitionDateDetails.show();

            }
        });

        // Inflate the layout for this fragment
        return ScheduleFragment;
    }

    private void DisplayTrainingDates(){


        for(String Dates: AllTrainingDates){

            //This programming statement has been adapted from DigitalOcean:
            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
            //Author: Anupam Chugh
            Calendar TrainingCalander = Calendar.getInstance();

            if(Dates !=null){
                //This programming statement has been adapted from DigitalOcean:
                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                //Author: Anupam Chugh
                TrainingCalander.set(Calendar.DAY_OF_MONTH,Integer.parseInt(Dates.substring(0,2)));

                switch(Dates.substring(3,11)){

                    case "January":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.JANUARY);
                        break;
                    case "February":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.FEBRUARY);
                        break;
                    case "March":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.MARCH);
                        break;
                    case "April":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.APRIL);
                        break;
                    case "May":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.MAY);
                        break;
                    case "June":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.JUNE);
                        break;
                    case "July":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.JULY);
                        break;
                    case "August":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.AUGUST);
                        break;
                    case "September":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.SEPTEMBER);
                        break;
                    case "October":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.OCTOBER);
                        break;
                    case "November":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.NOVEMBER);
                        break;
                    case "December":
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        TrainingCalander.set(Calendar.MONTH, Calendar.DECEMBER);
                        break;
                }
                //This programming statement has been adapted from DigitalOcean:
                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                //Author: Anupam Chugh
                TrainingCalander.set(Calendar.YEAR, Integer.parseInt(Dates.substring(12,16)));
                //This programming statement was adapted from GitHub:
                //Link: https://github.com/Applandeo/Material-Calendar-View
                //Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
                //Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
                // https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
                // https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
                TrainingDates.add(new EventDay(TrainingCalander, R.drawable.eunice2));
                //This programming statement was adapted from GitHub:
                //Link: https://github.com/Applandeo/Material-Calendar-View
                //Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
                //Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
                // https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
                // https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
                Schedule.setEvents(TrainingDates);

            }



        }

    }

    private void DisplayHipHopCompetitionDates(){

        HipHopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for(String Dates: HipHopCompetition){

                    //This programming statement has been adapted from DigitalOcean:
                    //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                    //Author: Anupam Chugh
                    Calendar HipHopCalander = Calendar.getInstance();

                    if(Dates !=null){
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        HipHopCalander.set(Calendar.DAY_OF_MONTH,Integer.parseInt(Dates.substring(0,2)));

                        switch(Dates.substring(3,11)){

                            case "January":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.JANUARY);
                                break;
                            case "February":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.FEBRUARY);
                                break;
                            case "March":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.MARCH);
                                break;
                            case "April":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.APRIL);
                                break;
                            case "May":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.MAY);
                                break;
                            case "June":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.JUNE);
                                break;
                            case "July":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.JULY);
                                break;
                            case "August":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.AUGUST);
                                break;
                            case "September":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.SEPTEMBER);
                                break;
                            case "October":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.OCTOBER);
                                break;
                            case "November":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.NOVEMBER);
                                break;
                            case "December":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HipHopCalander.set(Calendar.MONTH, Calendar.DECEMBER);
                                break;
                        }
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        HipHopCalander.set(Calendar.YEAR, Integer.parseInt(Dates.substring(12,16)));

                        //This programming statement was adapted from GitHub:
                        //Link: https://github.com/Applandeo/Material-Calendar-View
                        //Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
                        //Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
                                                 // https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
                                                 // https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
                        TestDates.add(new EventDay(HipHopCalander, R.drawable.round_image_button_hiphop, Color.parseColor("#228B22")));
                        //This programming statement was adapted from GitHub:
                        //Link: https://github.com/Applandeo/Material-Calendar-View
                        //Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
                        //Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
                        // https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
                        // https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
                        Schedule.setEvents(TestDates);


                    }



                }




            }
        });


    }

    private void DisplayModernCompetitionDates(){

        ModernButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            for(String Dates: ModernCompetition){

                //This programming statement has been adapted from DigitalOcean:
                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                //Author: Anupam Chugh
                Calendar ModernCalander = Calendar.getInstance();

                if(Dates !=null){
                    //This programming statement has been adapted from DigitalOcean:
                    //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                    //Author: Anupam Chugh
                    ModernCalander.set(Calendar.DAY_OF_MONTH,Integer.parseInt(Dates.substring(0,2)));

                    switch(Dates.substring(3,11)){

                        case "January":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.JANUARY);
                            break;
                        case "February":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.FEBRUARY);
                            break;
                        case "March":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.MARCH);
                            break;
                        case "April":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.APRIL);
                            break;
                        case "May":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.MAY);
                            break;
                        case "June":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.JUNE);
                            break;
                        case "July":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.JULY);
                            break;
                        case "August":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.AUGUST);
                            break;
                        case "September":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.SEPTEMBER);
                            break;
                        case "October":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.OCTOBER);
                            break;
                        case "November":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.NOVEMBER);
                            break;
                        case "December":
                            //This programming statement has been adapted from DigitalOcean:
                            //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                            //Author: Anupam Chugh
                            ModernCalander.set(Calendar.MONTH, Calendar.DECEMBER);
                            break;
                    }
                    //This programming statement has been adapted from DigitalOcean:
                    //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                    //Author: Anupam Chugh
                    ModernCalander.set(Calendar.YEAR, Integer.parseInt(Dates.substring(12,16)));
                    //This programming statement was adapted from GitHub:
                    //Link: https://github.com/Applandeo/Material-Calendar-View
                    //Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
                    //Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
                    // https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
                    // https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
                    TestDates.add( new EventDay(ModernCalander, R.drawable.round_image_button_modern));
                    //This programming statement was adapted from GitHub:
                    //Link: https://github.com/Applandeo/Material-Calendar-View
                    //Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
                    //Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
                    // https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
                    // https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
                    Schedule.setEvents(TestDates);
                }



            }


        }
        });

    }
    private void DisplayHPCompetitionDates(){

        HpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(String Dates: HPCompetition){
                    //This programming statement has been adapted from DigitalOcean:
                    //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                    //Author: Anupam Chugh
                    Calendar HPCalander = Calendar.getInstance();

                    if(Dates !=null){
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        HPCalander.set(Calendar.DAY_OF_MONTH,Integer.parseInt(Dates.substring(0,2)));

                        switch(Dates.substring(3,11)){

                            case "January":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.JANUARY);
                                break;
                            case "February":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.FEBRUARY);
                                break;
                            case "March":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.MARCH);
                                break;
                            case "April":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.APRIL);
                                break;
                            case "May":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.MAY);
                                break;
                            case "June":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.JUNE);
                                break;
                            case "July":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.JULY);
                                break;
                            case "August":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.AUGUST);
                                break;
                            case "September":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.SEPTEMBER);
                                break;
                            case "October":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.OCTOBER);
                                break;
                            case "November":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.NOVEMBER);
                                break;
                            case "December":
                                //This programming statement has been adapted from DigitalOcean:
                                //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                                //Author: Anupam Chugh
                                HPCalander.set(Calendar.MONTH, Calendar.DECEMBER);
                                break;
                        }
                        //This programming statement has been adapted from DigitalOcean:
                        //Link: https://www.digitalocean.com/community/tutorials/android-calendar-view
                        //Author: Anupam Chugh
                        HPCalander.set(Calendar.YEAR, Integer.parseInt(Dates.substring(12,16)));
                        //This programming statement was adapted from GitHub:
                        //Link: https://github.com/Applandeo/Material-Calendar-View
                        //Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
                        //Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
                        // https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
                        // https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
                        TestDates.add( new EventDay(HPCalander, R.drawable.round_image_button_hp));
                        //This programming statement was adapted from GitHub:
                        //Link: https://github.com/Applandeo/Material-Calendar-View
                        //Authors: kormateusz,Afur,thavelka,sahanaprabhakar-halodoc,EugeneGxUA,dryerflyer,gert-jan-conneqtech,mtrakal,HeikkiKangas,daividvleal,victor-accarini,sianis,Stjin,sliverbackz,domyn,EdricChan03,josemoncada87,nfdz,BoxResin,marcinszot
                        //Authors Profile Link:  https://github.com/kormateusz, https://github.com/Afur, https://github.com/thavelka, https://github.com/sahanaprabhakar-halodoc, https://github.com/EugeneGxUA, https://github.com/dryerflyer, https://github.com/gert-jan-conneqtech,
                        // https://github.com/mtrakal, https://github.com/HeikkiKangas, https://github.com/daividvleal, https://github.com/victor-accarini, https://github.com/sianis, https://github.com/Stjin, https://github.com/sliverbackz, https://github.com/domyn,
                        // https://github.com/EdricChan03, https://github.com/josemoncada87, https://github.com/nfdz, https://github.com/BoxResin, https://github.com/marcinszot
                        Schedule.setEvents(TestDates);
                    }


                }


            }
        });

    }

}
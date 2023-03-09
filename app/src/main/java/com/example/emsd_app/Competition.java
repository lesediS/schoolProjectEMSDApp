package com.example.emsd_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Competition extends Fragment {


    private Button Training;
    private Button Competition;
    private Button FitnessAndBody;
    private Button MessageBoard;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View CompetitionFragment = inflater.inflate(R.layout.fragment_competition, container, false);
        //Button Setup
        Training = CompetitionFragment.findViewById(R.id.Training);
        Competition = CompetitionFragment.findViewById(R.id.CompetitionDates);
        FitnessAndBody = CompetitionFragment.findViewById(R.id.FitnessAndBody);
        MessageBoard = CompetitionFragment.findViewById(R.id.MessageBoard);

        //Launch Windows
        OpenTrainingSchedule();
        OpenCompetition();
        OpenFitnessAndBody();
        OpenMessageBoard();

        // Inflate the layout for this fragment
        return CompetitionFragment;
    }


    private void OpenTrainingSchedule(){
        //This method was adapted from StackOverflow:
        //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
        //Author: Harsh Bhavsar
        //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
        Training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager Manager4= getFragmentManager();
                FragmentTransaction OpeningTheSchedulePage = Manager4.beginTransaction();
                Fragment Schedule = new Schedule();
                OpeningTheSchedulePage.replace(R.id.fragment_container,Schedule);
                OpeningTheSchedulePage.commit();
            }
        });


    }

    private void OpenCompetition(){
        //This method was adapted from StackOverflow:
        //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
        //Author: Harsh Bhavsar
        //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
        Competition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager Manager4= getFragmentManager();
                FragmentTransaction OpeningTheSchedulePage = Manager4.beginTransaction();
                Fragment Schedule = new Schedule();
                OpeningTheSchedulePage.replace(R.id.fragment_container,Schedule);
                OpeningTheSchedulePage.commit();
             }
        });

    }

    private void OpenFitnessAndBody(){
        //This method was adapted from StackOverflow:
        //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
        //Author: Harsh Bhavsar
        //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
        FitnessAndBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager Manager5= getFragmentManager();
                FragmentTransaction OpeningTheFitnessAndBodyPage = Manager5.beginTransaction();
                Fragment FitnessAndBody = new FitnessAndBody();
                OpeningTheFitnessAndBodyPage.replace(R.id.fragment_container,FitnessAndBody );
                OpeningTheFitnessAndBodyPage.commit();
            }
        });


    }

    private void OpenMessageBoard(){
        //This method was adapted from StackOverflow:
        //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
        //Author: Harsh Bhavsar
        //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
        MessageBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Competition", "Competition")).addToBackStack("tag").commit();

            }
        });

    }

}
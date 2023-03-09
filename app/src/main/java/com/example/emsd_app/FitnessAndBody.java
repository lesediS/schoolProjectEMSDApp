package com.example.emsd_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FitnessAndBody extends Fragment {

    private TextView FitnessTips;
    private TextView BodyTips;
    private Button Back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View FitnessAndBody = inflater.inflate(R.layout.fragment_fitness_and_body, container, false);

        FitnessTips = FitnessAndBody.findViewById(R.id.HealthTips);
        BodyTips = FitnessAndBody.findViewById(R.id.BodyTips);
        Back = FitnessAndBody.findViewById(R.id.ReturnButton);

        SetFitnessTips();
        SetBodyTips();

        //This method was adapted from StackOverflow:
        //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
        //Author: Harsh Bhavsar
        //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager Manager8 = getFragmentManager();
                FragmentTransaction ReturnToCompetitionPage = Manager8.beginTransaction();
                Fragment Competition = new Competition();
                ReturnToCompetitionPage.replace(R.id.fragment_container,Competition);
                ReturnToCompetitionPage.commit();
            }
        });

        // Inflate the layout for this fragment
        return FitnessAndBody;
    }

    private void SetFitnessTips(){


        FitnessTips.setText("Tip #1: Do not exercise on an empty stomach." + "\n" +"Tip #2: Stretch before you dance."+ "\n"+"Tip #3: Wear comfortable shoes.");

    }
    private void SetBodyTips(){


        BodyTips.setText("Tip #1: Try to maintain a balanced diet of fruits and vegetables during training days." + "\n" +"Tip #2: Perform cool-down stretches to reduce the tension in your body, after a training session. "+ "\n"+"Tip #3: Wear comfortable shoes.");

    }
}
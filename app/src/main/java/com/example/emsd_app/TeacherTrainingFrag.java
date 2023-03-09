package com.example.emsd_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class TeacherTrainingFrag extends Fragment {
    Button grade12, hipHopPre, hipHopAsso, modernPre, modernAsso;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_training, container, false);

        getActivity().setTitle("Teacher Training"); 
        grade12 = view.findViewById(R.id.grade12Btn);
        hipHopPre = view.findViewById(R.id.preAssoBtn);
        hipHopAsso = view.findViewById(R.id.assoBtn);
        modernPre = view.findViewById(R.id.preAssoModernBtn);
        modernAsso = view.findViewById(R.id.assoModernBtn);

        //Open message board when each is clicked
        grade12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern Grade 12", grade12.getText().toString())).addToBackStack("tag").commit();
            }
        });

        hipHopPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop Pre-Associate", hipHopPre.getText().toString())).addToBackStack("tag").commit();
            }
        });

        hipHopAsso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop Associate", hipHopAsso.getText().toString())).addToBackStack("tag").commit();
            }
        });

        modernPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern Pre-Associate", modernPre.getText().toString())).addToBackStack("tag").commit();
            }
        });

        modernAsso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern Associate", modernAsso.getText().toString())).addToBackStack("tag").commit();
            }
        });


        return view;
    }
}

package com.example.emsd_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Modern extends Fragment {

    Button buttonCreativeMovement, buttonTinyTots, buttonGrade1, buttonGrade3, buttonGrade4, buttonGrade5, buttonGrade2,
    buttonGrade6, buttonGrade7, buttonGrade8, buttonGrade9, buttonGrade10, buttonGrade11, buttonGrade12;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_modern, container, false);
        buttonCreativeMovement = view.findViewById(R.id.buttonCreativeMovement);
        buttonTinyTots = view.findViewById(R.id.buttonTinyTots);
        buttonGrade1 = view.findViewById(R.id.buttonGrade1);
        buttonGrade2 = view.findViewById(R.id.buttonGrade2);
        buttonGrade3 = view.findViewById(R.id.buttonGrade3);
        buttonGrade4 = view.findViewById(R.id.buttonGrade4);
        buttonGrade5 = view.findViewById(R.id.buttonGrade5);
        buttonGrade6 = view.findViewById(R.id.buttonGrade6);
        buttonGrade7 = view.findViewById(R.id.buttonGrade7);
        buttonGrade8 = view.findViewById(R.id.buttonGrade8);
        buttonGrade9 = view.findViewById(R.id.buttonGrade9);
        buttonGrade10 = view.findViewById(R.id.buttonGrade10);
        buttonGrade11 = view.findViewById(R.id.buttonGrade11);
        buttonGrade12 = view.findViewById(R.id.buttonGrade12);

        buttonCreativeMovement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonCreativeMovement.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonTinyTots.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonTinyTots.getText().toString()))
                        .addToBackStack("tag")
                        .commit();
            }
        });
        buttonGrade1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade1.getText().toString()))
                        .addToBackStack("tag").commit();
            }
        });
        buttonGrade2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade2.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade3.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade4.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade5.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade6.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade7.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade8.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade9.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade10.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade11.getText().toString())).addToBackStack("tag").commit();
            }
        });
        buttonGrade12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Modern",buttonGrade12.getText().toString())).addToBackStack("tag").commit();
            }
        });
        View rootView= inflater.inflate(R.layout.fragment_modern, container, false);

            getActivity().setTitle("Modern Class");


        return view;
    }
}

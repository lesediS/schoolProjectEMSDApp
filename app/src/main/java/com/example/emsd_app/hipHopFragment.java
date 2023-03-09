package com.example.emsd_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link hipHopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class hipHopFragment extends Fragment {

    //stepup -> btn12 are the hip hop levels
    private Button stepup, jumpin, btn1, btn2, btn3, btn4, btn5, btn6, btn7,
            btn8, btn9, btn10, btn11, btn12;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public hipHopFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment hipHopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static hipHopFragment newInstance(String param1, String param2) {
        hipHopFragment fragment = new hipHopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hip_hop, container, false);
        getActivity().setTitle("Hip Hop");
        stepup = view.findViewById(R.id.stepUpBtn);
        jumpin = view.findViewById(R.id.jumpInBtn);
        btn1 = view.findViewById(R.id.lvl1Btn);
        btn2 = view.findViewById(R.id.lvl2Btn);
        btn3 = view.findViewById(R.id.lvl3Btn);
        btn4 = view.findViewById(R.id.lvl4Btn);
        btn5 = view.findViewById(R.id.lvl5Btn);
        btn6 = view.findViewById(R.id.lvl6Btn);
        btn7 = view.findViewById(R.id.lvl7Btn);
        btn8 = view.findViewById(R.id.lvl8Btn);
        btn9 = view.findViewById(R.id.lvl9Btn);
        btn10 = view.findViewById(R.id.lvl10Btn);
        btn11 = view.findViewById(R.id.lvl11Btn);
        btn12 = view.findViewById(R.id.lvl12Btn);

        //Opening message board for each level (Step Up - Level 12)
        stepup.setOnClickListener(view1 -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new messageBoardFragment("Hip Hop", stepup.getText().toString())).addToBackStack("tag").commit());
        jumpin.setOnClickListener(view12 -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new messageBoardFragment("Hip Hop", jumpin.getText().toString())).addToBackStack("tag").commit()
        );
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn1.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn2.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn3.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn4.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn5.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn6.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn7.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn8.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn9.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Hip Hop", btn10.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Level 11", btn11.getText().toString())).addToBackStack("tag").commit();
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("Level 12", btn12.getText().toString())).addToBackStack("tag").commit();
            }
        });

        return view;
    }
}
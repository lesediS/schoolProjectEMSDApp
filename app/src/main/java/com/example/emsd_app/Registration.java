package com.example.emsd_app;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.emsd_app.DataAccess.EMDSDB;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Registration extends Fragment implements EMDSDB.UserValidation{

private EditText Username;
private EditText Password;
private Button Back;
private Button Register;

//This programming statement was adapted from StackOverflow:
//Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
//Author: Devchris
//Author Profile Link: https://stackoverflow.com/users/14488059/devchris
ExecutorService ExcecuteFunction = Executors.newSingleThreadExecutor();
//This programming statement was adapted from StackOverflow:
//Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
//Author: Devchris
//Author Profile Link: https://stackoverflow.com/users/14488059/devchris
Handler HandleOutput = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RegistrationFragment = inflater.inflate(R.layout.fragment_registration, container, false);

        Username = RegistrationFragment.findViewById(R.id.Username);

        Password = RegistrationFragment.findViewById(R.id.Password);

        Register = RegistrationFragment.findViewById(R.id.RegistrationButton);

        Back = RegistrationFragment.findViewById(R.id.Back);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Username.getText().toString().equals("") && !Password.getText().toString().equals("")) {

                    if (Password.getText().length() >= 6) {
                        com.example.emsd_app.BusinessLayer.Registration NewUser = new com.example.emsd_app.BusinessLayer.Registration();


                        //This programming statement was adapted from StackOverflow:
                        //Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
                        //Author: Devchris
                        //Author Profile Link: https://stackoverflow.com/users/14488059/devchris
                        ExcecuteFunction.execute(() ->{

                            NewUser.GetUsername(Username.getText().toString());
                            NewUser.GetPassword(Password.getText().toString());

                            //This programming statement was adapted from StackOverflow:
                            //Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
                            //Author: Devchris
                            //Author Profile Link: https://stackoverflow.com/users/14488059/devchris
                            HandleOutput.post(()->{

                                Log.d(TAG,"Registration Status: " + String.valueOf(EMDSDB.RegistrationStatus));

                                Register();

                            });
                        });


                    }else{
                        Toast.makeText(getContext(), "Passwords must contain at least 1 captial letter, 2 lowercase letters, 2 numbers and 1 symbol.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Registration Failed: No username/password has been entered." , Toast.LENGTH_SHORT).show();
                }
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ReturnToHomeScreen = new Intent(getActivity(), MainActivity.class);
                startActivity(ReturnToHomeScreen);
            }
        });
        // Inflate the layout for this fragment
        return RegistrationFragment;
    }

    public void Register(){


        if(EMDSDB.RegistrationStatus){

            Toast.makeText(getContext(), Username.getText() + "  has been registered successful", Toast.LENGTH_SHORT).show();

            Intent Home = new Intent(getContext(), MainActivity.class);
            startActivity(Home);

        }


    }






    }



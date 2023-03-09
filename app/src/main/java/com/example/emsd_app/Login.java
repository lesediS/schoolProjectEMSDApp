package com.example.emsd_app;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
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


public class Login extends Fragment implements EMDSDB.UserValidation {

 private EditText Username;
 private EditText Password;
 private Button Back;
 private Button Login;
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

 com.example.emsd_app.BusinessLayer.Login NewLogin = new com.example.emsd_app.BusinessLayer.Login();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View LoginFragment = inflater.inflate(R.layout.fragment_login, container, false);

        Username =  LoginFragment.findViewById(R.id.Username);

        Password =  LoginFragment.findViewById(R.id.Password);

        Login =  LoginFragment.findViewById(R.id.RegistrationButton);

        Back = LoginFragment.findViewById(R.id.Back);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Username.getText().toString().equals("") && !Password.getText().toString().equals("")) {

                    if (Password.getText().length() >= 6) {
                            //This programming statement was adapted from StackOverflow:
                            //Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
                            //Author: Devchris
                            //Author Profile Link: https://stackoverflow.com/users/14488059/devchris
                            ExecuteFunctions.execute(() ->{

                            NewLogin.GetUsername(Username.getText().toString());
                            NewLogin.GetPassword(Password.getText().toString());

                            //This programming statement was adapted from StackOverflow:
                            //Link: https://stackoverflow.com/questions/66715301/android-asynctask-api-deprecating-in-android-11-using-executor-as-alternative
                            //Author: Devchris
                            //Author Profile Link: https://stackoverflow.com/users/14488059/devchris
                            HandleOutput.post(()->{

                                Log.d(TAG,"Login Status: " + String.valueOf(EMDSDB.LoginStatus));

                                Login();
                            });
                        });


                    } else {
                        Toast.makeText(getContext(), "Passwords must contain at least 1 captial letter, 2 lowercase letters, 2 numbers and 1 symbol.", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(getContext(), "Login Failed: No username/password has been entered." , Toast.LENGTH_SHORT).show();

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
        return LoginFragment;



    }





    @SuppressLint("NotConstructor")
    public void Login(){

        if (EMDSDB.LoginStatus) {
            Intent Home = new Intent(getContext(), MainActivity.class);
            Home.putExtra("Current User", Username.getText());
            startActivity(Home);

            Toast.makeText(getContext(),Username.getText().toString()+ " has logged in successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Login Failed: An incorrect username/password has been entered." , Toast.LENGTH_SHORT).show();

        }

    }




}
package com.example.emsd_app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//@color/purple_200
    //#29a6c4
    private DrawerLayout drawer;
    public static int c = 0;
    private Button SignUp;
    private Button SignIn;
    private Button ClassRegistration;
    private ImageView LogoView;
    private ImageView ScheduleView;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("EMSD");

       // getSupportActionBar().setTitle("EMSD");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        if (!FirebaseApp.getApps(this).isEmpty() && c <= 0){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            c++;
        }

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Button Setup
        SignUp = findViewById(R.id.SignUp);
        SignIn = findViewById(R.id.SignIn);
        ClassRegistration = findViewById(R.id.RegisterForClass);

        //View Setup
        LogoView = findViewById(R.id.LogoView);
        //ScheduleView = findViewById(R.id.ScheduleView);


        //Launch Windows
        OpenLogin();
        OpenSignUp();
        OpenClassRegistration();



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    //Display the options menu.
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    private void enablePersistence() {
        // [START rtdb_enable_persistence]
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        // [END rtdb_enable_persistence]
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.newsItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new View_News()).commit();
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
               // Toast.makeText(this, "View news is selected", Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    //display the navigation menu items.
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        String sPackage;
        String appLink;
        switch(item.getItemId()){
            case R.id.homeItem:
                //This will be the main activity
                startActivity(new Intent(this, MainActivity.class));
                //getFragmentManager().popBackStack();
                break;
            case R.id.addNewsItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddNewsFrag()).commit();
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
                break;
            case R.id.hiphopItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new hipHopFragment()).commit();
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
                break;
            case R.id.modernItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Modern()).commit();
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
                break;
            case R.id.hpItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new messageBoardFragment("HP","HP")).commit();
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
                break;
            case R.id.compItem:

                //This programming statement was adapted from StackOverflow:
                //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
                //Author: Harsh Bhavsar
                //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
                FragmentManager Manager1= getFragmentManager();
                //This programming statement was adapted from StackOverflow:
                //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
                //Author: Harsh Bhavsar
                //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
                FragmentTransaction OpeningTheCompetitionsPage = getSupportFragmentManager().beginTransaction();
                //This programming statement was adapted from StackOverflow:
                //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
                //Author: Harsh Bhavsar
                //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
                Fragment Competitions = new Competition();
                //This programming statement was adapted from StackOverflow:
                //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
                //Author: Harsh Bhavsar
                //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
                OpeningTheCompetitionsPage.replace(R.id.fragment_container,Competitions);
                //This programming statement was adapted from StackOverflow:
                //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
                //Author: Harsh Bhavsar
                //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
                OpeningTheCompetitionsPage.commit();

                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);

                break;
            case R.id.teachtrainItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TeacherTrainingFrag()).commit();
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
                break;
            case R.id.calendarClassScheduleItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ClassScheduleFragment()).setReorderingAllowed(true).commit();
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //
                // ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
                break;
            case R.id.youtubeItem:
                Toast.makeText(this, "Go to YouTube page", Toast.LENGTH_SHORT).show();
                sPackage ="com.google.android.youtube";

                appLink = "https://www.youtube.com/channel/UCowgd3szih1qKiOolyYfTBg";
                openLink(appLink,sPackage,appLink);
                break;
            case R.id.instaItem:
                Toast.makeText(this, "Go to Instagram page", Toast.LENGTH_SHORT).show();
                 appLink = "https://www.instagram.com/emsd_dancestudio/";

                sPackage = "com.instagram.android";

                openLink(appLink,sPackage,appLink);
                break;
            case R.id.facebookItem:
                Toast.makeText(this, "Go to Facebook page", Toast.LENGTH_SHORT).show();
                 sPackage = "com.facebook.katana";
                 appLink = "fb://facewebmodal/f?href=https://www.facebook.com/eunicemaraisdance";
                String webLink = "https://www.facebook.com/eunicemaraisdance";
                openLink(appLink,sPackage,webLink);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openLink(String appLink, String sPackage, String webLink){
        try{

            Uri uri = Uri.parse(appLink);

            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setData(uri);

            intent.setPackage(sPackage);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }catch(ActivityNotFoundException e){

            Uri uri = Uri.parse(webLink);

            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setData(uri);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
    }

    public void OpenLogin(){


        SignIn.setOnClickListener(new View.OnClickListener() {
            //This method was adapted from StackOverflow:
            //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
            //Author: Harsh Bhavsar
            //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
            @Override
            public void onClick(View v) {

                FragmentManager Manager2 = getFragmentManager();
                FragmentTransaction OpeningTheLoginPage = getSupportFragmentManager().beginTransaction();
                Fragment Logins = new Login();
                OpeningTheLoginPage.replace(R.id.fragment_container,Logins);
                OpeningTheLoginPage.commit();


                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
            }
        });

        Intent GetLoggedInUser = getIntent();
        if(GetLoggedInUser.getStringExtra("Current User") != null){

            //Toast.makeText(getApplicationContext(), GetLoggedInUser.getStringExtra("Current User"), Toast.LENGTH_SHORT).show();
        }


    }

    private void OpenSignUp(){

        SignUp.setOnClickListener(new View.OnClickListener() {
            //This method was adapted from StackOverflow:
            //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
            //Author: Harsh Bhavsar
            //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
            @Override
            public void onClick(View v) {
                FragmentManager Manager3 = getFragmentManager();
                FragmentTransaction OpeningTheSignUpPage = getSupportFragmentManager().beginTransaction();
                Fragment Registrations = new Registration();
                OpeningTheSignUpPage.replace(R.id.fragment_container,Registrations);
                OpeningTheSignUpPage.commit();

                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
            }
        });


    }
    private void OpenClassRegistration(){


        ClassRegistration.setOnClickListener(new View.OnClickListener() {
            //This method was adapted from StackOverflow:
            //Link:https://stackoverflow.com/questions/38767565/how-to-open-fragment-on-a-button-click-from-an-activity-either-with-intent-and-w
            //Author: Harsh Bhavsar
            //Author Profile Link: https://stackoverflow.com/users/5996295/harsh-bhavsar
            @Override
            public void onClick(View v) {
                FragmentManager Manager3 = getFragmentManager();
                FragmentTransaction OpeningTheSignUpPage = getSupportFragmentManager().beginTransaction();
                Fragment Registrations = new Registration();
                OpeningTheSignUpPage.replace(R.id.fragment_container,Registrations);
                OpeningTheSignUpPage.commit();

                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignUp.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                SignIn.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                LogoView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                //ScheduleView.setVisibility(View.GONE);
                //This programming statement was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
                //Author: Alex Zaraos
                //Author Profile Link: https://stackoverflow.com/users/4847767/alex-zaraos
                ClassRegistration.setVisibility(View.GONE);
            }
        });

    }
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}
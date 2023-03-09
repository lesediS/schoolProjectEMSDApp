package com.example.emsd_app.DataAccess;

import static android.content.ContentValues.TAG;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.emsd_app.BusinessLayer.CompetitionsAndTraining;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EMDSDB {

    private String Username;
    private String HashedPassword;

    public static boolean LoginStatus;
    public static boolean RegistrationStatus;

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

    public static String DataFromService;


    public interface UserValidation {

        static boolean GetRegistrationStatus(boolean CurrentStatus) {
            RegistrationStatus = CurrentStatus;
            return RegistrationStatus;
        }

        static Boolean SetRegistrationStatus() {

            if (RegistrationStatus) {
                SetPositiveRegistrationStatus();

            } else {
                SetNegativeRegistrationStatus();

            }
            return null;
        }

        static boolean GetLoginStatus(boolean CurrentStatus) {
            LoginStatus = CurrentStatus;
            return LoginStatus;
        }

        static Boolean SetLoginStatus() {
            if (LoginStatus) {
                SetPositiveLoginStatus();

            } else {
                SetNegativeLoginStatus();

            }
            return null;
        }

        static boolean SetPositiveLoginStatus() {

            Log.d(TAG, "Login Successful");
            LoginStatus = true;
            return LoginStatus;
        }

        static boolean SetNegativeLoginStatus() {

            Log.d(TAG, "Login Failed");
            LoginStatus = false;
            return LoginStatus;
        }

        static boolean SetPositiveRegistrationStatus() {
            Log.d(TAG, "Registration Successful");
            RegistrationStatus = true;
            return RegistrationStatus;
        }

        static boolean SetNegativeRegistrationStatus() {

            Log.d(TAG, "Registration Failed");
            RegistrationStatus = false;
            return RegistrationStatus;
        }

        static ArrayList<String> SendCompetitionSchedules(ArrayList<String> CompetitionDates) {

            CompetitionsAndTraining.CompetitionDates = CompetitionDates;
            Log.d(TAG, "Dates Retrieved Successfully" + CompetitionsAndTraining.CompetitionDates.get(0));

            return CompetitionsAndTraining.CompetitionDates;
        }

        static ArrayList<String> SendTrainingSchedules(ArrayList<String> TrainingDates) {

            CompetitionsAndTraining.TrainingDates = TrainingDates;
            Log.d(TAG, "Dates Retrieved Successfully" + CompetitionsAndTraining.TrainingDates.get(0));

            return CompetitionsAndTraining.TrainingDates;
        }

    }

    public void CreateNewInstructor(String Username, String HashedPassword) {

        //This programming statement was adapted from Firebase:
        //Link: https://firebase.google.com/docs/database/android/read-and-write
        //Author: Google Developers
        FirebaseDatabase CloudDB = FirebaseDatabase.getInstance();
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        DatabaseReference EMSD = CloudDB.getReference("Instructors");

        //This programming statement was adapted from Firebase:
        //Link: https://firebase.google.com/docs/database/android/read-and-write
        //Author: Google Developers
        EMSD.child(Username).child("Username").push().setValue(Username);
        //This programming statement was adapted from Firebase:
        //Link: https://firebase.google.com/docs/database/android/read-and-write
        //Author: Google Developers
        EMSD.child(Username).child("Password").push().setValue(HashedPassword);


        //This programming statement was adapted from CalliCoder:
        //Link: https://www.callicoder.com/java-callable-and-future-tutorial/
        //Author: Rajeev Singh
        Callable<Boolean> HelloTest = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                //This method was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
                //Author: Carlton
                //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
                EMSD.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {

                            for (DataSnapshot User : snapshot.getChildren()) {

                                if (User.child(Username).exists()) {

                                    UserValidation.GetRegistrationStatus(false);
                                    UserValidation.SetRegistrationStatus();
                                    break;
                                } else {

                                    UserValidation.GetRegistrationStatus(true);
                                    UserValidation.SetRegistrationStatus();

                                    break;

                                }
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                //Original Input Validation Code
                //Bug #1
                //Always returns false, even through it may be true.
                //return UserValidation.SetRegistrationStatus();

                return UserValidation.SetPositiveRegistrationStatus();

            }
        };
        //This programming statement was adapted from CalliCoder:
        //Link: https://www.callicoder.com/java-callable-and-future-tutorial/
        //Author: Rajeev Singh
        Future<Boolean> TestExecutor = ExecuteFunctions.submit(HelloTest);

        DataFromService = null;
        try {
            //This programming statement was adapted from CalliCoder:
            //Link: https://www.callicoder.com/java-callable-and-future-tutorial/
            //Author: Rajeev Singh
            DataFromService = String.valueOf(TestExecutor.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void LoginNewInstructor(String Username, String HashedPassword) {

        //This programming statement was adapted from Firebase:
        //Link: https://firebase.google.com/docs/database/android/read-and-write
        //Author: Google Developers
        FirebaseDatabase CloudDB = FirebaseDatabase.getInstance();
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        DatabaseReference EMSD = CloudDB.getReference("Instructors");


        //This method was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        EMSD.child(Username).child("Username").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot Usernames : snapshot.getChildren()) {

                        if (Usernames.getValue(String.class).equals(Username)) {
                            Log.d(TAG, "Username Verification Successful");
                            break;
                        }else{
                            Log.d(TAG, "Username Verification Unsuccessful");
                            break;
                        }

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //This programming statement was adapted from CalliCoder:
        //Link: https://www.callicoder.com/java-callable-and-future-tutorial/
        //Author: Rajeev Singh
        Callable<Boolean> HelloTest = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                //This method was adapted from StackOverflow:
                //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
                //Author: Carlton
                //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
                EMSD.child(Username).child("Password").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        if (snapshot.exists()) {

                            for (DataSnapshot Passwords : snapshot.getChildren()) {

                                if(Passwords.getValue(String.class).equals(HashedPassword)) {

                                    UserValidation.GetLoginStatus(true);
                                    UserValidation.SetLoginStatus();
                                    break;
                                } else {
                                    UserValidation.GetLoginStatus(false);
                                    UserValidation.SetLoginStatus();

                                    break;
                                }
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });


                //Original Input Validation Code
                //Bug #1
                //Always returns false, even through it may be true.
                //return UserValidation.SetLoginStatus();

                return UserValidation.SetPositiveLoginStatus();
            }


        };
        //This programming statement was adapted from CalliCoder:
        //Link: https://www.callicoder.com/java-callable-and-future-tutorial/
        //Author: Rajeev Singh
        Future<Boolean> TestExecutor = ExecuteFunctions.submit(HelloTest);

        DataFromService = null;
        try {
            //This programming statement was adapted from CalliCoder:
            //Link: https://www.callicoder.com/java-callable-and-future-tutorial/
            //Author: Rajeev Singh
            DataFromService = String.valueOf(TestExecutor.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }





    }

    public void GetCompetitionSchedules() {

        //This programming statement was adapted from Firebase:
        //Link: https://firebase.google.com/docs/database/android/read-and-write
        //Author: Google Developers
        FirebaseDatabase CloudDB = FirebaseDatabase.getInstance();
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        DatabaseReference EMSD = CloudDB.getReference("Competitions");

        ArrayList<String> HipHopCompetitionDates = new ArrayList<String>();
        ArrayList<String> ModernCompetitionDates = new ArrayList<String>();
        ArrayList<String> HPCompetitionDates = new ArrayList<String>();

        //This method was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        EMSD.child("Hip-Hop").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot CompetitionDates : snapshot.getChildren()) {
                            Log.d(TAG, String.valueOf(CompetitionDates.getValue()));
                        HipHopCompetitionDates.add((String) CompetitionDates.getValue());
                        Log.d(TAG, "Hip Hop Dates retrieved successfully");
                        UserValidation.SendCompetitionSchedules(HipHopCompetitionDates);
                        break;
                    }

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //This method was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        EMSD.child("Modern").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot CompetitionDates : snapshot.getChildren()) {

                        ModernCompetitionDates.add((String) CompetitionDates.getValue());
                        UserValidation.SendCompetitionSchedules(ModernCompetitionDates);
                        Log.d(TAG, "Modern Dates retrieved successfully");
                        break;
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //This method was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        EMSD.child("HP").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot CompetitionDates : snapshot.getChildren()) {

                        HPCompetitionDates.add((String) CompetitionDates.getValue());
                        UserValidation.SendCompetitionSchedules(HPCompetitionDates);
                        Log.d(TAG, "HP Dates retrieved successfully");
                        break;
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void GetTrainingSchedules() {

        //This programming statement was adapted from Firebase:
        //Link: https://firebase.google.com/docs/database/android/read-and-write
        //Author: Google Developers
        FirebaseDatabase CloudDB = FirebaseDatabase.getInstance();
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        DatabaseReference EMSD = CloudDB.getReference("Training");

        ArrayList<String> TrainingDates = new ArrayList<String>();

        //This method was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        EMSD.child("Hip-Hop").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot Dates : snapshot.getChildren()) {

                        TrainingDates.add((String) Dates.getValue());
                        Log.d(TAG, "Hip-Hop Training Dates have been retrieved successfully");
                        UserValidation.SendTrainingSchedules(TrainingDates);
                        break;
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        //This method was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        EMSD.child("Modern").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot Dates : snapshot.getChildren()) {

                        TrainingDates.add((String) Dates.getValue());
                        Log.d(TAG, "Modern Training Dates have been retrieved successfully");
                        UserValidation.SendTrainingSchedules(TrainingDates);
                        break;
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        //This method was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/43592935/firebase-addlistenerforsinglevalueevent-keeps-getting-old-values-that-no-longe
        //Author: Carlton
        //Author Profile Link: https://stackoverflow.com/users/3940445/carlton
        EMSD.child("HP").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot Dates : snapshot.getChildren()) {

                        TrainingDates.add((String) Dates.getValue());
                        Log.d(TAG, "HP Training Dates have been retrieved successfully");
                        UserValidation.SendTrainingSchedules(TrainingDates);
                        break;
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



    }

}
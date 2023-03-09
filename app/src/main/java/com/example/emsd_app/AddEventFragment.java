package com.example.emsd_app;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEventFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText eventName, eventStartTime, eventEndTime, eventDate;
    private Button addEventbtn;
    private DatePickerDialog datePicker;
    private Calendar calendar;
    private int year, month, day;
    private String timeStart;
    private String timeEnd;
    TimePickerDialog picker;
    private Spinner spinnerClass;
    private Spinner spinnerLevel;
    private DatabaseReference databaseReference;

    public AddEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEventFragment newInstance(String param1, String param2) {
        AddEventFragment fragment = new AddEventFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_event, container, false);

        eventName = view.findViewById(R.id.editTextEventName);
        eventStartTime = view.findViewById(R.id.editTextEventTimeStart);
        eventEndTime = view.findViewById(R.id.editTextEventTimeEnd);
        addEventbtn = view.findViewById(R.id.buttonAddEvent);
        eventDate = view.findViewById(R.id.editTextEventDate);
        spinnerClass = view.findViewById(R.id.spinnerClass);
        spinnerLevel = view.findViewById(R.id.spinnerLevel);

        eventDate = view.findViewById(R.id.editTextEventDate);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        databaseReference = FirebaseDatabase.getInstance("https://emsd-app-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Calendar");

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        final String[] classType = {""};
        final String[] level = {""};

        List<String> categories = new ArrayList<String>();
        categories.add("Hip Hop");
        categories.add("Modern");
        categories.add("HP");
        categories.add("Competition");
        categories.add("Teacher Training");
        List<String> levels = new ArrayList<String>();


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext().getApplicationContext(), android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapterLevels = new ArrayAdapter<String>(getContext().getApplicationContext(), android.R.layout.simple_spinner_item, levels);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerClass.setAdapter(dataAdapter);

        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                levels.clear();
                String category = categories.get(i);
                int j;
                switch (category) {
                    case "Hip Hop":
                        levels.add("Step Up");
                        levels.add("Jump In");
                        levels.add("Get Down");
                         j = 4;
                        for(int k = 1; k < 13; k++ ){
                            levels.add(" level " + k);
                            j++;
                        }
                        classType[0] = "Hip Hop";
                        dataAdapterLevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // attaching data adapter to spinner
                        spinnerLevel.setAdapter(dataAdapterLevels);
                        spinnerLevel.setClickable(true);
                        break;
                    case "Modern":
                        levels.add("Creative Movement");
                        levels.add("Tiny Tots");

                         j = 3;
                        for(int k = 1; k < 13; k++ ){
                            levels.add(" Grade " + k);
                            j++;
                        }
                        classType[0] = "Modern";
                        dataAdapterLevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // attaching data adapter to spinner
                        spinnerLevel.setAdapter(dataAdapterLevels);
                        spinnerLevel.setClickable(true);
                        break;
                    case "HP":
                        classType[0] = "HP";
                        level[0] = "";
                        break;
                    case "Competition":
                        levels.add("Novice");
                        levels.add("Armature");
                        levels.add("Championship");
                        levels.add("World Trail");
                        levels.add("Training");
                        classType[0] = "Competition";
                        dataAdapterLevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // attaching data adapter to spinner
                        spinnerLevel.setAdapter(dataAdapterLevels);
                        spinnerLevel.setClickable(true);
                        break;

                    case "Teacher Training":
                        levels.add("Hip Hop : Pre-Associate");
                        levels.add("Hip Hop : Associate");
                        levels.add("Modern : Grade 12");
                        levels.add("Modern : Pre-Associate");
                        levels.add("Modern : Associate");
                        classType[0] = "Teacher Training";
                        dataAdapterLevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // attaching data adapter to spinner
                        spinnerLevel.setAdapter(dataAdapterLevels);
                        spinnerLevel.setClickable(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                level[0] = levels.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        eventStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @SuppressLint("NewApi")
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eventStartTime.setText(sHour + ":" + sMinute);
                                timeStart = LocalTime.of(sHour, sMinute).toString();
                                picker.dismiss();
                            }
                        }, hour, minutes, true);
                picker.show();

            }
        });

        eventEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                picker = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @SuppressLint("NewApi")
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                //picker.
                                eventEndTime.setText(sHour + ":" + sMinute);
                                timeEnd = LocalTime.of(sHour, sMinute).toString();
                                picker.dismiss();
                            }
                        }, hour, minutes, true);
                picker.show();

            }
        });

        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog(999);
                datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        showDate(i, i1+1, i2);
                    }
                },year, month+1, day);
                datePicker.show();
            }
        });
        addEventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventNameS = eventName.getText().toString();
                String dateS = eventDate.getText().toString();

                String[] dateParts = dateS.split("-");
                int year = Integer.parseInt(dateParts[2]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[0]);
                Log.d("AddTime", "year : " + year + " month : " + month + " day : " + day);
                Event event = new Event(databaseReference.push().getKey(),eventNameS,timeStart, timeEnd, year, month, day,classType[0],level[0]);
                //event.setClassType(classType[0]);
                //event.setLevel(level[0]);
                //event.setEventID(databaseReference.push().getKey());
                databaseReference.child(event.getEventID()).setValue(event);
                Toast.makeText(getContext().getApplicationContext(),"Event added to calendar",Toast.LENGTH_SHORT).show();
                Event.eventsList.add(event);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ClassScheduleFragment()).commit();

            }
        });
        return view;
    }

    private void showDate(int year, int month, int day) {
        Log.d("AddEventTime", "year : " + year + " month : " + month + " day : " + day);
        eventDate.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }
}
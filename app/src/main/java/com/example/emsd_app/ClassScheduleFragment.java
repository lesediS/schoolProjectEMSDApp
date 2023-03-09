package com.example.emsd_app;

import static com.example.emsd_app.Event.eventsList;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClassScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassScheduleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CalendarView calender;
    EventAdapter adapter;
    TextView date;
    EditText inputText;
    Button buttonAdd;
    RecyclerView recyclereventsList;
    DatabaseReference databaseReference;
    public static int currentMonth = 0;
    public static int currentYear = 0;
    public static int currentDay = 0;

    private int daysIndex = 0;
    private int monthsIndex = 0;
    private int yearIndex = 0;
    public ClassScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClassScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassScheduleFragment newInstance(String param1, String param2) {
        ClassScheduleFragment fragment = new ClassScheduleFragment();
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
        View view = inflater.inflate(R.layout.fragment_class_schedule, container, false);
        databaseReference = FirebaseDatabase.getInstance("https://emsd-app-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Calendar");
        databaseReference.keepSynced(true);
        loadFirebaseDatabase();
        getActivity().setTitle("Class Schedule");
        calender = view.findViewById(R.id.calenderView);
        //inputText = findViewById(R.id.editTextInput);
        buttonAdd = view.findViewById(R.id.buttonEvent);
        recyclereventsList = view.findViewById(R.id.recyclerViewEvent);
        //final View dayContent = findViewById(R.id.daysContent);

        final List<String> calenderString = new ArrayList<String>();

        final int[] years = new int [12];
        final int[] days = new int [31];
        final int[] months= new int[12];


        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                currentYear = i;
                currentMonth = i1;
                currentDay = i2;
                setEventAdapter();
                /*
                String dateS = (i1+1) + "-" + i2 + "-" +i;
                date.setText(dateS);
                for(int z = 0; z<31; z++){
                    if(days[z] == currentDay){
                        for(int g = 0; g <12; g++){
                            if(months[g] == currentMonth){
                                inputText.setText(calenderString.get(z));
                                return;
                            }
                        }

                    }
                }
                inputText.setText("");

                 */
            }


        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                days[daysIndex] =  currentDay;
                months[monthsIndex] = currentMonth;
                years[yearIndex] =  currentYear;
                calenderString.add(daysIndex, inputText.getText().toString());
                daysIndex++;
                monthsIndex++;
                yearIndex++;
                inputText.setText("");

                 */

                //Intent Intent = new Intent(ClassScheduleFragment.this,AddEventFragment.class);
                //startActivity(Intent);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddEventFragment()).setReorderingAllowed(true).addToBackStack("tag").commit();
            }
        });


        return view;
    }

    private void loadFirebaseDatabase(){
     databaseReference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             Event.eventsList.clear();
             for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                 Event event =  dataSnapshot.getValue(Event.class);
                 eventsList.add(event);
             }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {
             Toast.makeText(getContext(),"Database loading failed", Toast.LENGTH_SHORT).show();

         }
     });
    }
    private void setEventAdapter(){

        ArrayList<Event> dailyEvents = Event.eventsForDate(currentYear,currentMonth+1,currentDay);
        //loadFirebaseDatabase();
         adapter = new EventAdapter(getContext().getApplicationContext(), dailyEvents);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Event event = adapter.getPosition(viewHolder.getAdapterPosition());
                Query query = databaseReference.orderByChild("eventID").equalTo(event.getEventID());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot appleSnapshot: snapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();

                            Toast.makeText(getContext(),"Event deleted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),"Category not able to be deleted in database", Toast.LENGTH_SHORT).show();
                    }
                });
                eventsList.remove(event);
            }
        }).attachToRecyclerView(recyclereventsList);
        recyclereventsList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclereventsList.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        recyclereventsList.setAdapter(null);

    }

}
package com.example.emsd_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class View_News extends Fragment {

    private RecyclerView recyclerView;
    private NewsBoardAdapter newsBoardAdapter;
    private DatabaseReference mDatabaseRef;
   // private List<Upload> mUploads;
    private ArrayList<Upload>mUploads;
    /*Need to:
    Make sure that when client adds news, it must appear at the top.
    The client must be able to edit the news or delete the news she posts.
    Need to connect to firebase.
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_board, container, false);
        getActivity().setTitle("EMSD News");

         recyclerView = (RecyclerView) v.findViewById(R.id.newsRecyclerView);
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
        llm.scrollToPosition(0);
          mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads");
        mDatabaseRef.keepSynced(true);
        mDatabaseRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                mUploads.clear();//clear arraylist before adding data into it.

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Upload upload = ds.getValue(Upload.class);

                    mUploads.add(upload);
                }
                Collections.reverse(mUploads);
                newsBoardAdapter = new NewsBoardAdapter(getActivity(), mUploads);

                recyclerView.setAdapter(newsBoardAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError){
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

     return  v;
    }
}

/*
   <TextView
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:layout_marginTop="1dp"
            android:gravity="center"
            android:text="EMSD NEWS"
            android:textColor="#000000"
            android:textSize="26sp"
            android:textStyle="bold" />
 */
package com.example.emsd_app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emsd_app.Message;
import com.example.emsd_app.R;
import com.example.emsd_app.messageBoardAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link messageBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class messageBoardFragment extends Fragment implements messageBoardAdapter.OnItemClickListener {

    private EditText messageToSend;
    private ImageButton sendMsgBtn;
    private RecyclerView recyclerView;
    private List<Message> messagesList;
    private messageBoardAdapter MessageBoardAdapter;

    private StorageReference mStorage;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private TextView back;

    private String classType;
    private String level;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public messageBoardFragment() {
        // Required empty public constructor
    }

    public messageBoardFragment(String classType, String level){
        this.classType = classType;
        this.level = level;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment messageBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static messageBoardFragment newInstance(String param1, String param2) {
        messageBoardFragment fragment = new messageBoardFragment();
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
        View view =inflater.inflate(R.layout.fragment_message_board, container, false);
        recyclerView = view.findViewById(R.id.boardRecycler);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messagesList = new ArrayList<>();
        messageToSend = view.findViewById(R.id.messageEditTxt);
        sendMsgBtn = view.findViewById(R.id.sendMessageBtn);
        //back = view.findViewById(R.id.textViewBackBtn);
        //levelTextView = view.findViewById(R.id.textViewMessageLevel);
        //levelTextView.setText(level);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Messages");
        databaseReference.keepSynced(true);
        getActivity().setTitle(level);
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void sendMessage(){
        final String sentMsg = messageToSend.getText().toString().trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        final String sentDate = dateFormat.format(new Date());
        final String timeSent = timeFormat.format(new Date());
        Message message = new Message(sentDate, timeSent, sentMsg,classType.toLowerCase(),level);
        //TODO: Might need to be new Time

        if(!TextUtils.isEmpty(sentMsg)){
            DatabaseReference newMessage = databaseReference.push();
            Map<String, String> dataHash = new HashMap<>();

            dataHash.put("Message", sentMsg);
            dataHash.put("Date", sentDate);
            dataHash.put("Time", timeSent);

            newMessage.setValue(message);
        }
    }

    public void onStart() {
        super.onStart();
        Query query  = databaseReference.orderByChild("classType").equalTo(classType.toLowerCase());
        //Query query2 = databaseReference.orderByChild("level").equalTo(level);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message messagePost = snapshot.getValue(Message.class);
                //Collections.reverse(messagesList);
                if(messagePost.getLevel().equals(level)){
                messagesList.add(messagePost);
                //Collections.reverse(messagesList);

                MessageBoardAdapter = new messageBoardAdapter(getContext(), messagesList,messageBoardFragment.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(MessageBoardAdapter);
                MessageBoardAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message messagePost = snapshot.getValue(Message.class);
                //Collections.reverse(messagesList);
                if(messagePost.getLevel().equals(level)){
                    messagesList.add(messagePost);
                    //Collections.reverse(messagesList);

                    MessageBoardAdapter = new messageBoardAdapter(getContext().getApplicationContext(), messagesList,messageBoardFragment.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
                    recyclerView.setAdapter(MessageBoardAdapter);
                    MessageBoardAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Message messagePost = snapshot.getValue(Message.class);
                //Collections.reverse(messagesList);
                if(messagePost.getLevel().equals(level)){
                    messagesList.add(messagePost);
                    //Collections.reverse(messagesList);

                    MessageBoardAdapter = new messageBoardAdapter(getContext().getApplicationContext(), messagesList,messageBoardFragment.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
                    recyclerView.setAdapter(MessageBoardAdapter);
                    MessageBoardAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onStop(){
        super.onStop();
        messagesList.clear();
    }

    @Override
    public void onItemClick(View view, int position) {
        Message message = messagesList.get(position);
        if(URLUtil.isValidUrl(message.getMessageText())){
            if(message.getMessageText().contains("youtu.be") || message.getMessageText().contains("youtube")){
                String sPackage ="com.google.android.youtube";

                openLink(message.getMessageText(),sPackage,message.getMessageText());
            }
        }else{
            Log.d("URL not valid","message does not have valid url");
        }
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
}
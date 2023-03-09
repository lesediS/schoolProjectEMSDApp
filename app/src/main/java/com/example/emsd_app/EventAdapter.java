package com.example.emsd_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Event> mData;

    public EventAdapter(Context mContext, ArrayList<Event> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cell,parent,false);
        //view = inflater.inflate(R.layout.item, parent,false);

        return new EventAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Event event  = mData.get(position);
        holder.textViewEvent.setText(event.getName());
        holder.textViewEventClass.setText(event.getClassType()  + " " + event.getLevel());
        holder.textViewTimes.setText(event.getStartTime() + "-" + event.getEndTime());
    }
    public Event getPosition(int adapterPosition) { return mData.get(adapterPosition);    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /*
    public EventAdapter(@NonNull Context context, List<Event> events) {
        super(context,0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        Event event  = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell,parent,false);
        }

        TextView textViewEvent = convertView.findViewById(R.id.textViewEventCell);
        //ImageButton buttonDelete = convertView.findViewById(R.id.imageButtonDeleteEvent);


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event.eventsList.remove(event);
            }
        });



        String eventTitle = event.getName() + " " + CalenderUtils.formatTime(event.getStartTime()) + "-" + CalenderUtils.formatTime(event.getEndTime())
                + event.getClassType()  + " " + event.getLevel();
        Log.d("eventView",eventTitle);
        textViewEvent.setText(eventTitle);
        return convertView;
    }
    */

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewEvent;
        TextView textViewTimes;
        TextView textViewEventClass;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewEvent = itemView.findViewById(R.id.textViewEventCell);
            textViewTimes = itemView.findViewById(R.id.textViewEventCellTimes);
            textViewEventClass = itemView.findViewById(R.id.textViewClassLevels);
        }
    }
}

package com.example.emsd_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class messageBoardAdapter extends RecyclerView.Adapter<messageBoardAdapter.ViewHolder> {

    private Context context;
    private List<Message> messagesList;
    private  OnItemClickListener listener;


    public messageBoardAdapter(Context context, List<Message> messagesList, OnItemClickListener onItemClickListener){
        this.context = context;
        this.messagesList = messagesList;
        listener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    @NonNull
    @Override
    public messageBoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.messagelayout, parent, false);

        return new ViewHolder(view, context,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull messageBoardAdapter.ViewHolder holder, int position) {
        Message message = messagesList.get(position);
        holder.datestamp.setText(message.getDateStamp());
        holder.timestamp.setText(message.getTimeStamp());
        holder.message.setText(message.getMessageText());
    }

    public Message getPosition(int adapterPosition) {
        return   messagesList.get(adapterPosition);
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView datestamp;
        private TextView timestamp;
        private TextView message;
        OnItemClickListener onItemClickListener;
        private ViewHolder(View view, Context cont,  OnItemClickListener onItemClickListener){
            super(view);
            context = cont;
            datestamp = view.findViewById(R.id.board_date);
            timestamp = view.findViewById(R.id.textTimeStamp);
            message = view.findViewById(R.id.boardMessageText);

            this.onItemClickListener = onItemClickListener;
            //itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}

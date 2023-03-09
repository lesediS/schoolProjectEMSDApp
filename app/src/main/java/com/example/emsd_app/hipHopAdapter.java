package com.example.emsd_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class hipHopAdapter extends RecyclerView.Adapter<hipHopAdapter.ViewHolder> {

    private Context context;
    private List<HipHop> hipHopList;

    public hipHopAdapter(Context context, List<HipHop> hipHopList){
        this.context = context;
        this.hipHopList = hipHopList;
    }

    @NonNull
    @Override
    public hipHopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hiphoplayout, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull hipHopAdapter.ViewHolder holder, int position) {
        //Set buttons to levels
    }

    @Override
    public int getItemCount() {
        return hipHopList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ViewHolder(View view, Context cont){
            super(view);
            context = cont;
        }
    }
}

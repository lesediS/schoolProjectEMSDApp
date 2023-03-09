package com.example.emsd_app;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    ImageView newsImage;
    TextView descriptionNews;

    public NewsViewHolder(@NonNull View itemView){
        super(itemView);
        newsImage = itemView.findViewById(R.id.newsImage);
        descriptionNews = itemView.findViewById(R.id.newsDescriptionTV);
        }
}

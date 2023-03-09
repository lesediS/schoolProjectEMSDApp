package com.example.emsd_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsBoardAdapter extends RecyclerView.Adapter<NewsBoardAdapter.NewsViewHolder> {

   private  Context mcontext;
   private ArrayList<Upload> mUploads;

    public NewsBoardAdapter(Context context, ArrayList<Upload> uploads) {
       mcontext = context;
       mUploads = uploads;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.news_view, parent, false);
    return new NewsBoardAdapter.NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsBoardAdapter.NewsViewHolder holder, int position) {

        Upload uploadCurrent = mUploads.get(position);
        String id = uploadCurrent.getId();
        String description = uploadCurrent.getImgDescription();

        Picasso.with(mcontext)
                .load(uploadCurrent.getmImageUrl())
               // .fit()
               // .centerCrop()
                .into(holder.imageView);

        holder.displayDescription.setText(description);

        holder.deleteNewsPost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Confirm delete dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                builder.setTitle("Delete")
                        .setMessage("Are you sure you want to delete this Post")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                //begin delete
                                Toast.makeText(mcontext, "Deleting...", Toast.LENGTH_SHORT).show();
                                deletePost(uploadCurrent, holder);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        holder.updateNewsPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imageView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1600)
                        .create();

                View myView = dialogPlus.getHolderView();
               // EditText url = myView.findViewById(R.id.updateImgUrl);
                EditText descrip = myView.findViewById(R.id.updateDescrip);
                Button update = myView.findViewById(R.id.updateBtn);

                //url.setText(uploadCurrent.getmImageUrl());
                descrip.setText(uploadCurrent.getImgDescription());

                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Map<String,Object> map = new HashMap<>();
                       // map.put("mImageUrl", url.getText().toString());
                        map.put("imgDescription", descrip.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Uploads")
                                    .child(uploadCurrent.getId()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(mcontext, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(mcontext, "Error while updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });
            }
        });

    }

    private void deletePost(Upload upload, NewsBoardAdapter.NewsViewHolder holder){
        //get id of post to delete
        String id = upload.getId();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Uploads");
        ref.child(id)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //delete successfully
                        Toast.makeText(mcontext, "Successfully deleted...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed to delete
                        Toast.makeText(mcontext,""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        public TextView displayDescription;
        public ImageView imageView;
        public ImageButton deleteNewsPost;
        public ImageButton updateNewsPost;

        public NewsViewHolder(View itemView){
            super(itemView);
            displayDescription = itemView.findViewById(R.id.newsDescriptionTV);
            imageView = itemView.findViewById(R.id.newsImage);
            deleteNewsPost = itemView.findViewById(R.id.deleteNewsBtn);
            updateNewsPost = itemView.findViewById(R.id.editNewsBtn);
        }
    }

}
/*

    <EditText
        android:id="@+id/updateImgUrl"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:hint="Image URL"
        android:textSize="20sp" />
 */
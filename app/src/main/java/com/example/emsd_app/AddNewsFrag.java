package com.example.emsd_app;

import static android.app.appsearch.AppSearchResult.RESULT_OK;
import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

import android.Manifest;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentResolver;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddNewsFrag extends Fragment  {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton btnChoose;
    private Button btnUpload;
    private ImageView viewNews;
    private EditText newsDescrip;

    private Uri filePath;
    private StorageReference storageReference;
    private FirebaseStorage mStorage;
    Bitmap bitmap;
    UploadTask mUploadTask;

    private DatabaseReference mDatabaseRef;
    ActivityResultLauncher<String[]> activityResultLauncherPermissions;
    ActivityResultLauncher<Intent> activityResultLauncherGallery;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View addNews = inflater.inflate(R.layout.fragment_add_news, container, false);

        getActivity().setTitle("Add News");

        //storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads");

        btnChoose = addNews.findViewById(R.id.choosePicImgBtn);
        btnUpload = addNews.findViewById(R.id.uploadBtn);
        viewNews = addNews.findViewById(R.id.displayImg);
        newsDescrip = addNews.findViewById(R.id.descriptionET);

        //Need to get the client's permission to use their photos.
        String[] appPerms;
        appPerms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        btnChoose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                   activityResultLauncherPermissions.launch(appPerms);
                    Toast.makeText(getActivity(), "Click the camera button to add image", Toast.LENGTH_SHORT).show();
                }else{
                    showFileChooser();
                }

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mUploadTask != null && mUploadTask.isInProgress())
                {
                    Toast.makeText(getActivity(), "Upload in progress", Toast.LENGTH_SHORT).show();
                }else
                {
                    upLoadFile();
               }
            }
        });


        activityResultLauncherPermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
             Boolean isGranted = true;

             for(Boolean b : result.values()){
                 isGranted = isGranted && b;
             }

             if(isGranted){
                 upLoadFile();
             }
            }
        });

        activityResultLauncherGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                filePath = result.getData().getData();
                try
                {
                    viewNews.setImageURI(filePath);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    return addNews;
    }

    //Method for file chooser
    private void showFileChooser(){
        Intent intent = new Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI);
        activityResultLauncherGallery.launch(intent);
    }

    private String description="";

    private void upLoadFile() { //Method to allow the client to upload their image and description for the news board.

        if(filePath != null) {
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            long timestamp = System.currentTimeMillis();
            description = newsDescrip.getText().toString().trim();
            StorageReference ref = mStorage.getReferenceFromUrl("gs://emsd-app.appspot.com/Uploads");
            StorageReference descripRef = ref.child(description+".jpg");
            StorageReference descripImageRef = ref.child("Images/"+descripRef);

            descripRef.getName().equals(descripImageRef.getName());
            descripRef.getPath().equals(descripImageRef.getPath());

            viewNews.setDrawingCacheEnabled(true);
            viewNews.buildDrawingCache();

            bitmap =((BitmapDrawable) viewNews.getDrawable()).getBitmap();

            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
            byte[] data = byteArray.toByteArray();


          // mUploadTask = ref.putFile(filePath) //to check there is an upload currently running.
            mUploadTask = descripRef.putBytes(data);
            mUploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "File Uploaded Successfully", Toast.LENGTH_SHORT).show();

                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uriTask.isSuccessful());
                            String uploadPictureUrl = ""+uriTask.getResult();

                            uploadPicInfoToDb(uploadPictureUrl,timestamp);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                           // progressDialog.dismiss();
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress) +"% Uploaded...");

                        }
                    });
        }else
        {
            //display a error toast
            Toast.makeText(getActivity(),"No image selected. Pick an Image", Toast.LENGTH_SHORT).show();
        }
    }

    public String enteredDescription;

    public void uploadPicInfoToDb(String uploadPictureUrl, long timestamp) {
        enteredDescription = newsDescrip.getText().toString().trim();

       HashMap<String, Object> hashMap = new HashMap<>();
       hashMap.put("id", ""+timestamp);
       hashMap.put("mImageUrl",""+uploadPictureUrl);
       hashMap.put("imgDescription", ""+enteredDescription);

       mDatabaseRef.child(""+timestamp)
               .setValue(hashMap)
               .addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       Toast.makeText(getActivity(), "Successfully uploaded...", Toast.LENGTH_SHORT).show();
                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(getActivity(), "Failed to upload to Database due to "+e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });
    }
}

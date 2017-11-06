package com.sjsu.jese.parkhere.newPost;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sjsu.jese.parkhere.MainActivity;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostConfirmation extends Fragment {

    Button submitBtn;
    Button uploadImageBtn;
    ImageView postImageView;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mPostRef = database.getReference().child("Posts");
    private String postUid = mPostRef.push().getKey();

    DatabaseReference mCustomerRef = database.getReference().child("Customers");
    FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();

    ProgressDialog progressDialog;

    public NewPostConfirmation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_post_confirmation, container, false);
        submitBtn = (Button) v.findViewById(R.id.submitBtn);
        uploadImageBtn = (Button) v.findViewById(R.id.uploadImageBtn);
        postImageView = (ImageView) v.findViewById(R.id.postImageView);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Post newPost = ((NewPostActivity) getActivity()).newPost;
                newPost.setOwnerUid(currUser.getUid());
                addToDatabase(newPost);
            }
        });



        uploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    startGallery();
                }
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        NewPostActivity activity = (NewPostActivity) getActivity();
        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            Uri returnUri = data.getData();
            try {
                Bitmap bitmapImage = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), returnUri);
                postImageView.setImageBitmap(bitmapImage);
            } catch (IOException e) {

            }
        }
    }
    private void startGallery() {
        Intent cameraIntent = new Intent();
        cameraIntent.setType("image/*");
        cameraIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(cameraIntent, "Select Picture"), 1000);
    }

    private void addToDatabase(Post newPost) {
        // add new post to "Posts" in firebase
        mPostRef.child(postUid).setValue(newPost);
        // add new post to logged in user's "posts"
        mCustomerRef.child(currUser.getUid()).child("posts").child(postUid).setValue(true);
        progressDialog.dismiss();
        Toast.makeText(getActivity(), "Post Creation Successful",
                Toast.LENGTH_SHORT).show();
        ((NewPostActivity) getActivity()).onBackPressed();
    }

}

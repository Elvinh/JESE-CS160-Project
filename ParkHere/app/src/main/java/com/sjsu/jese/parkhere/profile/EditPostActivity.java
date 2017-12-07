package com.sjsu.jese.parkhere.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.newPost.NewPostActivity;

import java.io.IOException;

public class EditPostActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference mPostsRef = mDatabase.getReference("Posts");
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    private StorageReference storageRef = storage.getReference();
    private StorageReference imageRef;

    private ImageView mImage;
    private Bitmap postImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        final String postID = getIntent().getStringExtra("POST_ID");

        imageRef = storageRef.child("images/" + postID + "/post_image");

        final EditText title = (EditText) findViewById(R.id.item_title);
        final EditText description = (EditText) findViewById(R.id.descriptionText);
        final EditText price = (EditText) findViewById(R.id.item_price);
        final Button mSubmitBtn = (Button) findViewById(R.id.submit_edit);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        mImage = (ImageView) findViewById(R.id.imageView3);

        final String[] options = {"Compact", "Mini Van", "SUV"};

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Glide.with(this /* context */)
                .using(new FirebaseImageLoader())
                .load(imageRef)
                .into(mImage);

        mPostsRef.child(postID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                title.setText((String) dataSnapshot.child("title").getValue());
                price.setText(((Long) dataSnapshot.child("dailyRate").getValue()).toString());
                Address address = dataSnapshot.child("address").getValue(Address.class);
                //mAddress.setText(address.getStreetAddress() + ", " + address.getCity() + ", " + address.getZipCode());
                //mStartDate.setText("Available from: " + (String) dataSnapshot.child("dateAvailable").getValue());
                //mEndDate.setText("Ends: " + (String) dataSnapshot.child("dateEnd").getValue());
                description.setText((String) dataSnapshot.child("shortDescription").getValue());

                int spinnerPosition = adapter.getPosition((String ) dataSnapshot.child("carSize").getValue());
                //set the default according to value
                spinner.setSelection(spinnerPosition);

                mSubmitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPostsRef.child(postID).child("title").setValue(title.getText().toString());
                        mPostsRef.child(postID).child("shortDescription").setValue(description.getText().toString());
                        mPostsRef.child(postID).child("dailyRate").setValue(Double.parseDouble(price.getText().toString()));
                        mPostsRef.child(postID).child("carSize").setValue(spinner.getSelectedItem().toString());
                        finish();
                    }
                });

                mImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(ActivityCompat.checkSelfPermission(EditPostActivity.this,
                                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                        {
                    /*requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);*/
                        }
                        else {
                            startGallery();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            Uri returnUri = data.getData();
            try {
                postImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), returnUri);
                mImage.setImageBitmap(postImage);
                imageRef.putFile(returnUri);
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
}

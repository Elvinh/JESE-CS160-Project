package com.sjsu.jese.parkhere.profile;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.newPost.NewPostActivity;

public class EditPostActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference mPostsRef = mDatabase.getReference("Posts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        final String postID = getIntent().getStringExtra("POST_ID");

        final EditText title = (EditText) findViewById(R.id.item_title);
        final EditText description = (EditText) findViewById(R.id.descriptionText);
        final EditText price = (EditText) findViewById(R.id.item_price);
        final Button mSubmitBtn = (Button) findViewById(R.id.submit_edit);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final String[] options = {"Compact", "Mini Van", "SUV"};

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);



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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

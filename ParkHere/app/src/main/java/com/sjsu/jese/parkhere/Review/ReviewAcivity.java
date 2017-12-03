package com.sjsu.jese.parkhere.Review;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Review;

/**
 * Created by evankardos on 12/2/17.
 */

public class ReviewAcivity extends AppCompatActivity {

    final private String CURRENTPOST="com.sjsu.parkHere.current.Post";

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mReviewRef = database.getReference().child("Reviews");
    private String reviewUid = mReviewRef.push().getKey();

    DatabaseReference mPostsRef = database.getReference().child("Posts");
    FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();


    String currentPost;
    private EditText mDescrib;
    private EditText mTitle;
    private Button mSubmit;
    private RatingBar mRate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_create_review);

        currentPost= getIntent().getStringExtra(CURRENTPOST);
        Log.d("Review","current post id"+currentPost);

         mDescrib=(EditText)findViewById(R.id.review_decribtion);
         mTitle=(EditText)findViewById(R.id.review_title);
         mRate= (RatingBar) findViewById(R.id._review_rating);
        mSubmit=(Button) findViewById(R.id.submit_review_Btn);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Review r= new Review(currUser.getUid(),
                        mTitle.getText().toString(),
                        mDescrib.getText().toString(),
                        (int)mRate.getRating());

                addToDataBase(r);
            }
        });

    }

    private void addToDataBase(Review newReview) {
    mReviewRef.child(reviewUid).setValue(newReview);
    mPostsRef.child(currentPost).child("reviews").child(reviewUid).setValue(true);
        Toast.makeText(getApplicationContext(), "Review Submitted",
                Toast.LENGTH_SHORT).show();
    finish();
    }
}

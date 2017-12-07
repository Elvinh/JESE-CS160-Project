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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.model.Review;

import java.util.ArrayList;

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

        currentPost= getIntent().getStringExtra("POST_ID");
        Log.d("Review","current post id"+currentPost);

         mDescrib=(EditText)findViewById(R.id.review_decribtion);
         mTitle=(EditText)findViewById(R.id.review_title);
         mRate= (RatingBar) findViewById(R.id._review_rating);
        mSubmit=(Button) findViewById(R.id.submit_review_Btn);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_text = mTitle.getText().toString().trim();
                String describ_text = mDescrib.getText().toString().trim();

               boolean title_fault= title_text.isEmpty() ||
                       title_text.length() == 0 ||
                       title_text.equals("") ||
                       title_text == null;

               boolean describ_fault= describ_text.isEmpty() ||
                       describ_text.length() == 0 ||
                       describ_text.equals("") ||
                       describ_text == null;

                if(describ_fault || title_fault) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the criteria",
                            Toast.LENGTH_SHORT).show();
                }
                else{

                    Review r = new Review(currUser.getUid(), title_text,
                            describ_text, mRate.getRating());
                    r.setPostId(currentPost);
                    addToDataBase(r);
                }
            }
        });

    }

    private void addToDataBase(Review newReview) {
        mReviewRef.child(reviewUid).setValue(newReview);
        mPostsRef.child(currentPost).child("reviews").child(reviewUid).setValue(true);

        mReviewRef.orderByChild("postId").equalTo(currentPost).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Float> values = new ArrayList<>();
                float avgRating = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Review review = child.getValue(Review.class);
                    values.add(review.getRate());
                }
                for(float value: values) {
                    avgRating = avgRating + value;
                }

                if(values.size() != 0)
                    avgRating = avgRating/values.size();

                mPostsRef.child(currentPost).child("averageRating").setValue(avgRating);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Toast.makeText(getApplicationContext(), "Review Submitted",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}

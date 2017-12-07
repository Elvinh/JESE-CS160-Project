package com.sjsu.jese.parkhere.Review;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.Book.BookActivity;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.model.Review;

import java.util.ArrayList;

/**
 * Created by Elton Vinh on 12/05/2017.
 */

public class PostReviewsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference mReviewsRef = mDatabase.getReference("Reviews");

    FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_reviews);

        final String postID = getIntent().getStringExtra("POST_ID");

        Button newReview = (Button) findViewById(R.id.new_review);
        newReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNewReview(postID);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.my_posts_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mReviewsRef.orderByChild("postId").equalTo(postID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Review> values = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Review review = child.getValue(Review.class);
                    values.add(review);
                    Log.d("[posts]", review.getPostId());
                }
                Log.d("hey", "im here");

                mRecyclerView.setAdapter(new PostReviewsAdapter(values));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void toNewReview(String postID) {
        Intent intent= new Intent(this, ReviewAcivity.class);
        intent.putExtra("POST_ID", postID);
        startActivity(intent);
    }
}

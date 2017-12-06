package com.sjsu.jese.parkhere.profile;

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
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;

import java.util.ArrayList;

/**
 * Created by Elton Vinh on 12/05/2017.
 */

public class MyPostsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference mPostsRef = mDatabase.getReference("Posts");

    FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);

        TextView screenName = (TextView) findViewById(R.id.screenNameText);
        screenName.setText(currUser.getDisplayName() +"'s Posts");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_posts_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPostsRef.orderByChild("ownerUid").equalTo(currUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Post> values = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post post = child.getValue(Post.class);
                    post.setUid(child.getKey());
                    values.add(post);
                    Log.d("[posts]", post.getUid());
                }
                Log.d("hey", "im here");

                mRecyclerView.setAdapter(new MyPostsAdapter(values));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

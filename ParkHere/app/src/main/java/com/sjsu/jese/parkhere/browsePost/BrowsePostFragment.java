package com.sjsu.jese.parkhere.browsePost;

import android.icu.util.Freezable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by evank on 10/21/2017.
 */

public class BrowsePostFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference mPostsRef = mDatabase.getReference("Posts");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_browse_post, container, false);


        setUpDefaultBrowseResult(v);
        setUpSearch();

/*
        mPosts= new PostData();
        mAdapter= new PostAdapter(mPosts.getPosts());
        Log.e("browse","created browse");
        Log.e("browse", String.valueOf(mPosts.getPosts().size()));

        mRecyclerView = (RecyclerView) v.findViewById(R.id.posts_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
*/
        return v;
    }

    private void setUpDefaultBrowseResult(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.posts_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        mPostsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Post> values = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post post = child.getValue(Post.class);
                    post.setUid(child.getKey());
                    values.add(post);
                }

                mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), values));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUpSearch() {
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        EditText etPlace = (EditText)autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input);
        etPlace.setHint("Search by city.");

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("USA")
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();

        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Hey", "Place: " + place.getName().toString());
                mPostsRef.orderByChild("address/city").equalTo(place.getName().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<Post> values = new ArrayList<>();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Post post = child.getValue(Post.class);
                            post.setUid(child.getKey());
                            values.add(post);
                        }

                        mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), values));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Hey", "An error occurred: " + status);
            }
        });
    }
}

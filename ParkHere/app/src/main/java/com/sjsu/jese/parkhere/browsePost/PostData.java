package com.sjsu.jese.parkhere.browsePost;


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.model.Post;

import java.util.ArrayList;

/**
 * Created by evankardos on 11/4/17.
 */

public class PostData {
    private DatabaseReference mFirebaseRef;
    private ArrayList<Post> mPosts;

    private final ValueEventListener mValueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                Post post = singleSnapshot.getValue(Post.class);
                mPosts.add(post);
                Log.d("postdata", "getAllData: "+ mPosts.size());
            }

            Log.d("postdata", "called the listner");
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };




    public PostData() {
        mPosts = new ArrayList<>();
        mFirebaseRef= FirebaseDatabase.getInstance().getReference();
        mFirebaseRef.child("Posts").addListenerForSingleValueEvent(mValueEventListener);

        Log.d("postdata", "created everything");
    }

    private void getAllData(DataSnapshot dataSnapshot){
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
            Post post = singleSnapshot.getValue(Post.class);
            mPosts.add(post);
        }
        Log.d("postdata", "getAllData: "+mPosts.size());
    }


    public ArrayList<Post> getPosts() {
        return mPosts;
    }

    public Post getPostByID(String id){
        for (Post p: mPosts) {
            //if(p.getId().equals(id))
                return p;
        }
        return null;
    }
}


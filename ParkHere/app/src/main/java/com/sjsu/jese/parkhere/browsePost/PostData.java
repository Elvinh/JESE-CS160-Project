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

    public PostData() {
        mPosts = new ArrayList<>();
        mFirebaseRef= FirebaseDatabase.getInstance().getReference().child("Posts");
       mFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               getAllData(dataSnapshot);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

    }

    private void getAllData(DataSnapshot dataSnapshot){
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
            Post post = singleSnapshot.getValue(Post.class);
            mPosts.add(post);
        }
        Log.d("poop", String.valueOf(mPosts.size()));
    }


    public ArrayList<Post> getPosts() {
        return mPosts;
    }

    public Post getPostByID(String id){
        for (Post p: mPosts) {
            if(p.getId().equals(id))
                return p;
        }
        return null;
    }
}


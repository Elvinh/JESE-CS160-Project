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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by evank on 10/21/2017.
 */

public class BrowsePostFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private PostData mPosts;

    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_browse_post, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.posts_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mPostsRef = mDatabase.getReference("Posts");

        mPostsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Post> values = new ArrayList<>();
                for(DataSnapshot child: dataSnapshot.getChildren()) {
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



    private class PostHolder extends RecyclerView.ViewHolder{
       private TextView mAddress;
       private TextView mPrice;

        public PostHolder(View itemView) {
            super(itemView);
            mAddress=(TextView) itemView.findViewById(R.id.address);
            //mPrice=(TextView) itemView.findViewById(R.id.price);
        }
        public void bindPost(Post p){
            mAddress.setText(p.getAddress().toString());
            mPrice.setText(Double.toString(p.getDailyRate()));
        }
    }


    private class PostAdapter extends RecyclerView.Adapter<PostHolder> implements View.OnClickListener{
        private ArrayList< Post> mPost;

        public PostAdapter(ArrayList<Post> posts){
            mPost=posts;
        }

        @Override
        public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).
                    inflate(R.layout.post_item, parent, false);
            return new PostHolder(view);
        }

        @Override
        public void onBindViewHolder(PostHolder holder, int position) {
            holder.bindPost(mPost.get(position));
        }

        @Override
        public int getItemCount() {
            return mPost.size();
        }

        @Override
        public void onClick(View v) {

        }
    }
}

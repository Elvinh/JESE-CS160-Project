package com.sjsu.jese.parkhere.browsePost;

import android.icu.util.Freezable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView rv;
    private PostAdapter mAdapter;
    private ArrayList<String> a= new ArrayList<>();

    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_browse_post, container, false);
        rv = (RecyclerView) v.findViewById(R.id.posts_recycle_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //updateUI();

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mPostsRef = mDatabase.getReference("Posts");

        mPostsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Post> values = new ArrayList<>();
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    Post post = child.getValue(Post.class);
                     values.add(post);
                }

                rv.setAdapter(new RecyclerViewAdapter(values));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }


    public void updateUI(){

        a.add("hello");
        a.add("why");
        a.add("test");
        a.add("hello");
        a.add("why");
        a.add("test");
        a.add("hello");
        a.add("why");
        a.add("test");
        a.add("hello");
        a.add("why");
        a.add("test");
        mAdapter=new PostAdapter(a);
        rv.setAdapter(mAdapter);
    }

    private class PostHolder extends RecyclerView.ViewHolder{
       private  CardView cv;
       private TextView tv;

        public PostHolder(View itemView) {
            super(itemView);
            cv=(CardView) itemView.findViewById(R.id.card_view);
            tv=(TextView) itemView.findViewById(R.id.address);
        }
        public void bindPost(String i){
            tv.setText(i);
        }
    }

    private class PostAdapter extends RecyclerView.Adapter<PostHolder>{
        private ArrayList< String> mPost;

        public PostAdapter(ArrayList<String> posts){
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
    }
}

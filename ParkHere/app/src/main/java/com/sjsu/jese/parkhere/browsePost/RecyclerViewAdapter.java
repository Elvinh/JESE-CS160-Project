package com.sjsu.jese.parkhere.browsePost;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.postDetails.PostDetailActivity;

import java.util.ArrayList;

/**
 * Created by Elton on 11/5/2017.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Post> posts;
    private Context context;

    RecyclerViewAdapter(Context context, ArrayList<Post> values) {
        this.context = context;
        this.posts = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(posts.get(position).getTitle());
        holder.currPost = posts.get(position);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private Post currPost;
        private View view;

        ViewHolder (View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.item_title);
            view.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
                    Log.d("Title", currPost.getTitle());
                   Intent toPostDetails = new Intent(context, PostDetailActivity.class);
                   toPostDetails.putExtra("POST_ID", currPost.getUid());
                   context.startActivity(toPostDetails);
               }
            });
        }
    }
}

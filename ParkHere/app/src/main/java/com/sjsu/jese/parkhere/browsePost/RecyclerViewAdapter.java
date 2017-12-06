package com.sjsu.jese.parkhere.browsePost;


import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("images/" + posts.get(position).getUid() + "/post_image");

        holder.size.setText("FITS " + posts.get(position).getCarSize().toUpperCase() + " CAR");
        holder.name.setText(posts.get(position).getTitle());
        holder.price.setText("$" + String.valueOf((int)posts.get(position).getDailyRate()) + " per hour");
        holder.currPost = posts.get(position);
        Glide.with(context /* context */)
                .using(new FirebaseImageLoader())
                .load(imageRef)
                .into(holder.postImage);
        holder.averageRating.setRating((float) posts.get(position).getAverageRating());
        holder.averageRating.setIsIndicator(true);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private TextView size;
        private Post currPost;
        private View view;
        private ImageView postImage;
        private RatingBar averageRating;

        ViewHolder (View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.item_title);
            price = (TextView) itemView.findViewById(R.id.item_price);
            size = (TextView) itemView.findViewById(R.id.item_size);
            postImage = (ImageView) itemView.findViewById(R.id.imageView3);
            averageRating = (RatingBar) itemView.findViewById(R.id.review_rating);

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

package com.sjsu.jese.parkhere.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.postDetails.PostDetailActivity;

import java.util.ArrayList;

/**
 * Created by Elton on 11/5/2017.
 */

class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.ViewHolder> {
    private ArrayList<Post> posts;
    private Context context;

    MyPostsAdapter(ArrayList<Post> values) {
        this.posts = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("hey", "im here");
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_post_item,parent,false));

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
        holder.averageRating.setRating( posts.get(position).getAverageRating());
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
        private Button deletePost;
        private Button editPost;

        private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        private final DatabaseReference mPostsRef = mDatabase.getReference("Posts");
        private final DatabaseReference mCustomersRef = mDatabase.getReference("Customers");
        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        ViewHolder (View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.item_title);
            price = (TextView) itemView.findViewById(R.id.item_price);
            size = (TextView) itemView.findViewById(R.id.item_size);
            postImage = (ImageView) itemView.findViewById(R.id.imageView3);
            averageRating = (RatingBar) itemView.findViewById(R.id.review_rating);
            deletePost = (Button) itemView.findViewById(R.id.delete_post);
            editPost = (Button) itemView.findViewById(R.id.edit_post);

            deletePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Confirm")
                            .setMessage("Are you sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mPostsRef.child(currPost.getUid()).removeValue();
                                    mCustomersRef.child(currUser.getUid()).child("posts").child(currPost.getUid()).removeValue();
                                }

                            })
                            .setNegativeButton("No", null)
                            .show();


                }
            });

            editPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toPostDetails = new Intent(context, EditPostActivity.class);
                    toPostDetails.putExtra("POST_ID", currPost.getUid());
                    context.startActivity(toPostDetails);
                }
            });

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

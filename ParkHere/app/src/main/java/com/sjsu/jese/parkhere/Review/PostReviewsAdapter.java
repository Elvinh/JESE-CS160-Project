package com.sjsu.jese.parkhere.Review;

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
import com.sjsu.jese.parkhere.Book.Reservation;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.model.Review;
import com.sjsu.jese.parkhere.model.modelReservation;
import com.sjsu.jese.parkhere.postDetails.PostDetailActivity;

import java.util.ArrayList;

/**
 * Created by Elton on 11/5/2017.
 */

class PostReviewsAdapter extends RecyclerView.Adapter<PostReviewsAdapter.ViewHolder> {
    private ArrayList<Review> reviews;
    private Context context;

    PostReviewsAdapter(ArrayList<Review> values) {
        this.reviews = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("hey", "im here");
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent,false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(reviews.get(position).getTitle());
        holder.content.setText(reviews.get(position).getDescription());
        holder.ratingBar.setRating(reviews.get(position).getRate());
        holder.review = reviews.get(position);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView content;
        private RatingBar ratingBar;
        private Review review;
        private View view;

        ViewHolder (View itemView) {
            super(itemView);
            view = itemView;
            title = (TextView) itemView.findViewById(R.id.item_title);
            content = (TextView) itemView.findViewById(R.id.item_content);
            ratingBar = (RatingBar) itemView.findViewById(R.id.review_rating);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("Title", currReservation.getTitle());
                    //Intent toPostDetails = new Intent(context, PostDetailActivity.class);
                    //toPostDetails.putExtra("POST_ID", currReservation.getPostID());
                    //context.startActivity(toPostDetails);
                }
            });
        }
    }
}

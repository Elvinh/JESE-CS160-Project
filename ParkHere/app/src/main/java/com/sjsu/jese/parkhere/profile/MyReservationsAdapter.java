package com.sjsu.jese.parkhere.profile;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sjsu.jese.parkhere.Book.Reservation;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.model.modelReservation;
import com.sjsu.jese.parkhere.postDetails.PostDetailActivity;

import java.util.ArrayList;

/**
 * Created by Elton on 11/5/2017.
 */

class MyReservationsAdapter extends RecyclerView.Adapter<MyReservationsAdapter.ViewHolder> {
    private ArrayList<modelReservation> reservations;
    private Context context;

    MyReservationsAdapter(ArrayList<modelReservation> values) {
        this.reservations = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("hey", "im here");
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent,false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //FirebaseStorage storage = FirebaseStorage.getInstance();
        //StorageReference storageRef = storage.getReference();
        //StorageReference imageRef = storageRef.child("images/" + reservations.get(position).getUid() + "/post_image");

        //holder.size.setText("FITS " + reservations.get(position).getCarSize().toUpperCase() + " CAR");
        holder.day.setText(reservations.get(position).getDay());
        holder.time.setText(reservations.get(position).getTimeStart() + " - " + reservations.get(position).getTimeEnd());
        holder.currReservation = reservations.get(position);
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView day;
        private TextView time;
        private TextView size;
        private modelReservation currReservation;
        private View view;

        ViewHolder (View itemView) {
            super(itemView);
            view = itemView;
            day = (TextView) itemView.findViewById(R.id.item_day);
            time = (TextView) itemView.findViewById(R.id.item_time);
            size = (TextView) itemView.findViewById(R.id.item_size);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("Title", currReservation.getTitle());
                    Intent toPostDetails = new Intent(context, PostDetailActivity.class);
                    toPostDetails.putExtra("POST_ID", currReservation.getPostID());
                    context.startActivity(toPostDetails);
                }
            });
        }
    }
}

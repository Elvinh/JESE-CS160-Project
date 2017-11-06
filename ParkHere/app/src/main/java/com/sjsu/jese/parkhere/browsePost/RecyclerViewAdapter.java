package com.sjsu.jese.parkhere.browsePost;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;

import java.util.ArrayList;

/**
 * Created by Elton on 11/5/2017.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Post> values;

    RecyclerViewAdapter(ArrayList<Post> values) {
        this.values = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(values.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        ViewHolder (View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_title);
        }
    }
}

package com.sjsu.jese.parkhere.newPost;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostConfirmation extends Fragment {

    Button submitBtn;

    public NewPostConfirmation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_post_confirmation, container, false);

        submitBtn = (Button) v.findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post newPost = ((NewPostActivity) getActivity()).newPost;
                Log.d("Post",  newPost.getTitle() + newPost.getCarSize() + newPost.getDailyRate() + newPost.getDateAvailable() + newPost.getDateEnd());
            }
        });
        return v;
    }

}

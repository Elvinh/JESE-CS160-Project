package com.sjsu.jese.parkhere.newPost;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostGetTitleFragment extends Fragment {

    Button nextButton;
    EditText titleField;
    EditText descriptionField;

    public NewPostGetTitleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_post_get_title, container, false);

        titleField = (EditText) v.findViewById(R.id.titleField);
        descriptionField = (EditText) v.findViewById(R.id.descriptionField);

        nextButton = (Button) v.findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("address", titleField.getText().toString() + descriptionField.getText().toString());
                Post newPost = ((NewPostActivity)getActivity()).newPost;
               newPost.setTitle(titleField.getText().toString());
                NewPostConfirmation fragment = new NewPostConfirmation();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.newPost, fragment);
                fragmentTransaction.commit();
            }
        });
        return v;
    }


}

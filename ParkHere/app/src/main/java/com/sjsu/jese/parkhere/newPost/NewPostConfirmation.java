package com.sjsu.jese.parkhere.newPost;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.login.LoginActivity;
import com.sjsu.jese.parkhere.model.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostConfirmation extends Fragment {

    Button submitBtn;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mPostRef = database.getReference().child("Posts");
    private String postUid = mPostRef.push().getKey();

    DatabaseReference mCustomerRef = database.getReference().child("Customers");
    FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();

    ProgressDialog progressDialog;

    public NewPostConfirmation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_post_confirmation, container, false);
        submitBtn = (Button) v.findViewById(R.id.submitBtn);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Post newPost = ((NewPostActivity) getActivity()).newPost;
                Log.d("Post",  newPost.getTitle() + newPost.getCarSize() + newPost.getDailyRate() + newPost.getDateAvailable() + newPost.getDateEnd());
                addToDatabase(newPost);
            }
        });
        return v;
    }

    private void addToDatabase(Post newPost) {
        // add new post to "Posts" in firebase
        mPostRef.child(postUid).setValue(newPost);
        // add new post to logged in user's "posts"
        mCustomerRef.child(currUser.getUid()).child("posts").child(postUid).setValue(true);
        progressDialog.dismiss();
        Toast.makeText(getActivity(), "Post Creation Successful",
                Toast.LENGTH_SHORT).show();
        ((NewPostActivity) getActivity()).onBackPressed();
    }

}

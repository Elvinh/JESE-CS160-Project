package com.sjsu.jese.parkhere.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.login.LoginActivity;
import com.sjsu.jese.parkhere.newPost.NewPostGetDateFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileViewFragment extends Fragment {

    RelativeLayout logoutLayout;
    RelativeLayout myPostsLayout;
    TextView screenNameText;
    FirebaseAuth mAuth;
    public ProfileViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_view, container, false);

        mAuth = FirebaseAuth.getInstance();

        logoutLayout = (RelativeLayout) v.findViewById(R.id.logoutLayout);
        myPostsLayout = (RelativeLayout) v.findViewById(R.id.myPostsLayout);

        screenNameText = (TextView) v.findViewById(R.id.screenNameText);
        screenNameText.setText(mAuth.getCurrentUser().getDisplayName());

        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });

        myPostsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), MyPostsActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    // When button id = logoutBtn clicked logouts user and goes to LoginActivity
    public void toLogin() {
        mAuth.signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }
}

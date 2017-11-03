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
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostGetAddressFragment extends Fragment {

    EditText countryField;
    EditText streetField;
    EditText cityField;
    EditText stateField;
    EditText zipField;
    Button nextButton;

    public NewPostGetAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_post_get_address, container, false);

        countryField = (EditText) v.findViewById(R.id.countryField);
        streetField = (EditText) v.findViewById(R.id.streetField);
        cityField = (EditText) v.findViewById(R.id.cityField);
        stateField = (EditText) v.findViewById(R.id.stateField);
        zipField = (EditText) v.findViewById(R.id.zipField);

        nextButton = (Button) v.findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("address", countryField.getText().toString() + stateField.getText().toString() + zipField.getText().toString());
                Post newPost = ((NewPostActivity)getActivity()).newPost;
                newPost.setAddress(new Address(streetField.getText().toString(), cityField.getText().toString(),
                        stateField.getText().toString(), Integer.parseInt(zipField.getText().toString()), countryField.getText().toString()));
                NewPostGetTitleFragment fragment = new NewPostGetTitleFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.newPost, fragment);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

}

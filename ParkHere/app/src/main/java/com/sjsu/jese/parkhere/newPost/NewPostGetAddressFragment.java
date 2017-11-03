package com.sjsu.jese.parkhere.newPost;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
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

    TextInputLayout countryLayout;
    TextInputLayout streetLayout;
    TextInputLayout cityLayout;
    TextInputLayout stateLayout;
    TextInputLayout zipLayout;

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

        countryLayout = (TextInputLayout) v.findViewById(R.id.input_layout_country);
        streetLayout = (TextInputLayout) v.findViewById(R.id.input_layout_street);
        cityLayout = (TextInputLayout) v.findViewById(R.id.input_layout_city);
        stateLayout = (TextInputLayout) v.findViewById(R.id.input_layout_state);
        zipLayout = (TextInputLayout) v.findViewById(R.id.input_layout_zip);


        nextButton = (Button) v.findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()) {
                    Log.d("address", countryField.getText().toString() + stateField.getText().toString() + zipField.getText().toString());
                    Post newPost = ((NewPostActivity) getActivity()).newPost;
                    newPost.setAddress(new Address(streetField.getText().toString(), cityField.getText().toString(),
                            stateField.getText().toString(), Integer.parseInt(zipField.getText().toString()), countryField.getText().toString()));
                    NewPostGetTitleFragment fragment = new NewPostGetTitleFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.newPost, fragment);
                    fragmentTransaction.commit();
                }
            }
        });
        return v;
    }

    private  boolean validateForm() {
        boolean valid = true;

        String field = countryField.getText().toString();
        if (TextUtils.isEmpty(field)) {
            //emailField.setError("Required.");
            countryLayout.setError("Required.");
            valid = false;
        } else {
            //emailField.setError(null);
            countryLayout.setError(null);
        }

        field = streetField.getText().toString();
        if (TextUtils.isEmpty(field)) {
            //emailField.setError("Required.");
            streetLayout.setError("Required.");
            valid = false;
        } else {
            //emailField.setError(null);
            streetLayout.setError(null);
        }

        field = cityField.getText().toString();
        if (TextUtils.isEmpty(field)) {
            //emailField.setError("Required.");
            cityLayout.setError("Required.");
            valid = false;
        } else {
            //emailField.setError(null);
            cityLayout.setError(null);
        }

        field = zipField.getText().toString();
        if (TextUtils.isEmpty(field)) {
            //emailField.setError("Required.");
            zipLayout.setError("Required.");
            valid = false;
        } else {
            //emailField.setError(null);
            zipLayout.setError(null);
        }

        try {
            Integer.parseInt(field);
        }catch (NumberFormatException e) {
            zipLayout.setError("Not a valid zip code.");
            valid = false;
        }

        return valid;
    }
}

package com.sjsu.jese.parkhere.newPost;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostGetSizeFragment extends Fragment {

    Button nextButton;
    EditText rateField;
    TextInputLayout inputLayoutRate;
    Spinner spinner;

    public NewPostGetSizeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_post_get_size, container, false);

        nextButton = (Button) v.findViewById(R.id.nextBtn);
        rateField = (EditText) v.findViewById(R.id.rateField);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        inputLayoutRate = (TextInputLayout) v.findViewById(R.id.input_layout_rate);


        String[] options = {"Compact", "Mini Van", "SUV"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, options);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Post newPost = ((NewPostActivity)getActivity()).newPost;
                newPost.setCarSize(parent.getItemAtPosition(pos).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()) {

                    NewPostGetDateFragment fragment = new NewPostGetDateFragment();
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

        String rate = rateField.getText().toString();
        if (TextUtils.isEmpty(rate)) {
            //emailField.setError("Required.");
            inputLayoutRate.setError("Required.");
            valid = false;
        } else {
            //emailField.setError(null);
            inputLayoutRate.setError(null);
        }

        try {
            Post newPost = ((NewPostActivity) getActivity()).newPost;
            newPost.setDailyRate(Double.parseDouble(rate));
        } catch (NumberFormatException e) {
            inputLayoutRate.setError("Not a number.");
            valid = false;
        }

        return valid;
    }
}

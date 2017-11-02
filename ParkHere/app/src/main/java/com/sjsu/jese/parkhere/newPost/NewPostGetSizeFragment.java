package com.sjsu.jese.parkhere.newPost;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sjsu.jese.parkhere.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostGetSizeFragment extends Fragment {

    Button nextButton;
    EditText rateField;
    OnDataPass dataPasser;

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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(rateField.getText().toString());
                NewPostGetDateFragment fragment = new NewPostGetDateFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.newPost, fragment);
                fragmentTransaction.commit();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
    public interface OnDataPass {
        public void onDataPass(String data);
    }
    public void passData(String data) {
        dataPasser.onDataPass(data);
    }


}

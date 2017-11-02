package com.sjsu.jese.parkhere.newPost;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.sjsu.jese.parkhere.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostGetDateFragment extends Fragment {

    Button nextButton;
    TextInputLayout startDateLayout;
    TextInputLayout endDateLayout;
    EditText startDateText;
    EditText endDateText;
    Calendar startDateCalender;
    Calendar endDateCalender;
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener endDate;
    public NewPostGetDateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_post_date, container, false);

        startDateLayout = (TextInputLayout) v.findViewById(R.id.input_layout_start_date);
        endDateLayout = (TextInputLayout) v.findViewById(R.id.input_layout_end_date);

        startDateText = (EditText) v.findViewById(R.id.startDateField);
        endDateText = (EditText) v.findViewById(R.id.endDateField);

        startDateCalender = Calendar.getInstance();
        endDateCalender = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                startDateCalender.set(Calendar.YEAR, year);
                startDateCalender.set(Calendar.MONTH, monthOfYear);
                startDateCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateStartDate();
            }
        };

        endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int dayOfMonth, int monthOfYear) {
                endDateCalender.set(Calendar.YEAR, year);
                endDateCalender.set(Calendar.MONTH, monthOfYear);
                endDateCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEndDate();
            }
        };

        startDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, startDateCalender
                        .get(Calendar.YEAR), startDateCalender.get(Calendar.MONTH),
                        startDateCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), endDate, endDateCalender
                        .get(Calendar.YEAR), endDateCalender.get(Calendar.MONTH),
                        endDateCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        nextButton = (Button) v.findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPostGetAddressFragment fragment = new NewPostGetAddressFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.newPost, fragment);
                fragmentTransaction.commit();
            }
        });

        return v;
    }
    private void updateStartDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startDateText.setText(sdf.format(startDateCalender.getTime()));
    }
    private void updateEndDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endDateText.setText(sdf.format(endDateCalender.getTime()));
    }



}

package com.sjsu.jese.parkhere.newPost;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Post;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostGetDateFragment extends Fragment {

    Button nextButton;

    Calendar startDateCalender;
    Calendar endDateCalender;

    // For date
    TextInputLayout startDateLayout;
    TextInputLayout endDateLayout;
    EditText startDateText;
    EditText endDateText;
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener endDate;

    // For time
    TextInputLayout startTimeLayout;
    TextInputLayout endTimeLayout;
    EditText startTimeText;
    EditText endTimeText;
    TimePickerDialog.OnTimeSetListener startTime;
    TimePickerDialog.OnTimeSetListener endTime;

    public NewPostGetDateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_post_date, container, false);

        startDateCalender = Calendar.getInstance();
        endDateCalender = Calendar.getInstance();

        getDate(v);
        getTime(v);

        // Submit date and time
        nextButton = (Button) v.findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()) {
                    Post newPost = ((NewPostActivity) getActivity()).newPost;
                    newPost.setDateAvailable(startDateCalender.getTime().toString());
                    newPost.setDateEnd(endDateCalender.getTime().toString());

                    NewPostGetAddressFragment fragment = new NewPostGetAddressFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.newPost, fragment);
                    fragmentTransaction.commit();
                }


            }
        });

        return v;
    }

    private void getDate(View v) {
        /** Get Date **/
        startDateLayout = (TextInputLayout) v.findViewById(R.id.input_layout_start_date);
        endDateLayout = (TextInputLayout) v.findViewById(R.id.input_layout_end_date);

        startDateText = (EditText) v.findViewById(R.id.startDateField);
        endDateText = (EditText) v.findViewById(R.id.endDateField);

        // intialize dates text views with current date
        updateStartDate();
        updateEndDate();

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
        /** End Get Date **/
    }

    private void getTime(View v) {
        /** Get Date **/
        startTimeLayout = (TextInputLayout) v.findViewById(R.id.input_layout_start_time);
        endTimeLayout = (TextInputLayout) v.findViewById(R.id.input_layout_end_time);

        startTimeText = (EditText) v.findViewById(R.id.startTimeField);
        endTimeText = (EditText) v.findViewById(R.id.endTimeField);

        // intialize dates text views with current date
        updateStartTime();
        updateEndTime();

        startTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                // TODO Auto-generated method stub
                startDateCalender.set(Calendar.HOUR_OF_DAY, hour);
                startDateCalender.set(Calendar.MINUTE, minute);
                updateStartTime();
            }
        };

        endTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                endDateCalender.set(Calendar.HOUR_OF_DAY, hour);
                endDateCalender.set(Calendar.MINUTE, minute);
                updateEndTime();
            }
        };

        startTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), startTime, startDateCalender.get(Calendar.HOUR_OF_DAY),
                        startDateCalender.get(Calendar.MINUTE),
                        DateFormat.is24HourFormat(getActivity())).show();
            }
        });

        endTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), endTime, endDateCalender.get(Calendar.HOUR_OF_DAY),
                        endDateCalender.get(Calendar.MINUTE),
                        DateFormat.is24HourFormat(getActivity())).show();
            }
        });
        /** End Get Date **/
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

    private void updateStartTime() {
        String myFormat = "HH:mm a"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startTimeText.setText(sdf.format(startDateCalender.getTime()));

    }

    private void updateEndTime() {
        String myFormat = "HH:mm a"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endTimeText.setText(sdf.format(endDateCalender.getTime()));
    }

    private  boolean validateForm() {
        boolean valid = true;

        String date = startDateText.getText().toString();
        if (TextUtils.isEmpty(date)) {
            //emailField.setError("Required.");
            startDateLayout.setError("Required.");
            valid = false;
        } else {
            //emailField.setError(null);
            startDateLayout.setError(null);
        }

        date = endDateText.getText().toString();
        if (TextUtils.isEmpty(date)) {
            //emailField.setError("Required.");
            endDateLayout.setError("Required.");
            valid = false;
        } else {
            //emailField.setError(null);
            endDateLayout.setError(null);
        }

        date = startTimeText.getText().toString();
        if (TextUtils.isEmpty(date)) {
            //emailField.setError("Required.");
            startTimeLayout.setError("Required.");
            valid = false;
        } else {
            //emailField.setError(null);
            startTimeLayout.setError(null);
        }

        date = endDateText.getText().toString();
        if (TextUtils.isEmpty(date)) {
            //emailField.setError("Required.");
            endTimeLayout.setError("Required.");
            valid = false;
        } else {
            //emailField.setError(null);
            endTimeLayout.setError(null);
        }

        return valid;
    }

}

package com.sjsu.jese.parkhere.Book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sjsu.jese.parkhere.R;

public class BookActivity extends AppCompatActivity {
    final TextView mDay=(TextView)findViewById(R.id.ServiceDateField);
    final TextView mStartTime=(TextView)findViewById(R.id.startDateField);
    final TextView mEndTime=(TextView)findViewById(R.id.endTimeField);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
    }
}

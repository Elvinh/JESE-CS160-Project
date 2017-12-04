package com.sjsu.jese.parkhere.Book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.model.modelReservation;
import com.sjsu.jese.parkhere.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class BookActivity extends AppCompatActivity {
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reservationRef = mRootRef.child("Reservations");
    final DatabaseReference CustReservation = mRootRef.child("Customers");
    FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
    private String resID = reservationRef.push().getKey();
    String CurrID =currUser.getUid();
    //FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    //final DatabaseReference mPost = mDatabase.getReference("Posts").child(postId);
    String postID="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        TextView mDay=(TextView)findViewById(R.id.ServiceDateField);
        TextView mStartTime=(TextView)findViewById(R.id.startTimeField);
        TextView mEndTime=(TextView)findViewById(R.id.endTimeField);
        Button subBtn=(Button) findViewById(R.id.submitBtn);

        postID = getIntent().getStringExtra("POST_ID");
        Log.d("coolio", "imHere");
        CustReservation.child(CurrID).child("Reservations").child(resID).setValue(true);
        //String STime= mStartTime.toString();


        modelReservation a=new modelReservation(CurrID, mDay.getText().toString(), mStartTime.getText().toString(),mEndTime.getText().toString(), postID);
        reservationRef.child(resID).setValue(a);

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toConfirm();
            }
        });
    }
    public void addBooking(){
        //modelReservation a=new modelReservation("a","a","a","a","a");


    }
    public void toConfirm()
    {
        Intent intent= new Intent(this,BookActivity.class); //needs to connect to confirmation page
        intent.putExtra("POST_ID",postID);
        startActivity(intent);
    }
    private void DateFormat()
    {
        //NewPostGetDateFragment
        /**
        private void updateStartDate(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startDateText.setText(sdf.format(startDateCalender.getTime()));

        }
        private void updateStartTime()
        {
        String myFormat = "HH:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startTimeText.setText(sdf.format(startDateCalender.getTime()));
        }
        **/
    }
}

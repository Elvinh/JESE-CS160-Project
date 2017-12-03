package com.sjsu.jese.parkhere.Book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sjsu.jese.parkhere.model.Post;
import com.sjsu.jese.parkhere.model.modelReservation;
import com.sjsu.jese.parkhere.R;

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

    //final TextView mDay=(TextView)findViewById(R.id.ServiceDateField);
    //final TextView mStartTime=(TextView)findViewById(R.id.startDateField);
    //final TextView mEndTime=(TextView)findViewById(R.id.endTimeField);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        postID = getIntent().getStringExtra("POST_ID");
        Log.d("coolio", "imHere");
        CustReservation.child(CurrID).child("Reservations").setValue("a");
        modelReservation a=new modelReservation("a","a","a","a", postID);
        reservationRef.child(resID).setValue(a);


    }
    public void addBooking(){
        //modelReservation a=new modelReservation("a","a","a","a","a");


    }
}

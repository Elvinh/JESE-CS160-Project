package com.sjsu.jese.parkhere;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.model.Customer;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myCustomerRef = mRootRef.child("Customers");

    private static final String TAG = "MyActivity";

    TextView mCustomerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomerTextView = (TextView) findViewById(R.id.customerTextView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        myCustomerRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Customer customer = dataSnapshot.getValue(Customer.class);
                mCustomerTextView.setText(customer.getName());
                Log.d(TAG, "Value is: " + customer.getName());
                Log.d(TAG, "Value is: " + customer.getEmail());
                Log.d(TAG, "Value is: " + customer.getPhone());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, BrowsePostActivity.class);
        startActivity(intent);
    }


}

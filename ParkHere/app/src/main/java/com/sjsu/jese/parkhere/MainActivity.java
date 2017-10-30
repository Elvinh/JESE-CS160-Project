package com.sjsu.jese.parkhere;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.model.Address;
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
        final Button newAddressButton = (Button) findViewById(R.id.newAddressButton);

        newAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address address = new Address("1512 Sun Ln", "San Jose", "CA",95132, "United States");
                Customer customer = new Customer("Elton Vinh", "eltonv93@gmail.com", 2648907, address);
                CustomerDAO.getInstance().addAddress(customer);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myCustomerRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Customer customer = dataSnapshot.getValue(Customer.class);
                mCustomerTextView.setText(customer.getName());
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

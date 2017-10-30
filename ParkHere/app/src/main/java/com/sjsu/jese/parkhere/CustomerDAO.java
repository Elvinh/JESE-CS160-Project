package com.sjsu.jese.parkhere;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Customer;

import java.util.ArrayList;

/**
 * Created by Elton on 10/29/2017.
 */

public class CustomerDAO {
    private static final CustomerDAO instance = new CustomerDAO();
    private static final String TAG = "MyActivity";

    private CustomerDAO() {}

    public static CustomerDAO getInstance() {
        return instance;
    }
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference addressesRef = mRootRef.child("Customers");
    private String key = addressesRef.push().getKey(); // generates a unique key

    public void addCustomer(Customer customer) {
        addressesRef.child("eltonv93@gmail").setValue(customer);
    }

    public void getAllCustomer() {
        addressesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Customer customer = singleSnapshot.getValue(Customer.class);
                        Log.d(TAG, "Value is: " + customer.getEmail());
                        // do whatever with the data
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}

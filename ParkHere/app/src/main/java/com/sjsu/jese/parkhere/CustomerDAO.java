package com.sjsu.jese.parkhere;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Customer;

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

    public void addAddress(Customer customer) {
        Log.d(TAG, "Value is: " + customer.getEmail());
        addressesRef.child("1").setValue(customer);
    }


}

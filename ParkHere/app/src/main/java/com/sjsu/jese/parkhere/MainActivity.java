package com.sjsu.jese.parkhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.browsePost.BrowsePostActivity;
import com.sjsu.jese.parkhere.login.LoginActivity;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Customer;
import com.sjsu.jese.parkhere.newPost.NewPostActivity;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myCustomerRef = mRootRef.child("Customers");

    private static final String TAG = "MyActivity";

    TextView mCustomerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomerTextView = (TextView) findViewById(R.id.customerTextView);
        final Button newCustomerButton = (Button) findViewById(R.id.newAddressButton);

        // add new customer to Firebase database. Input is currently hardcoded in
        newCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address address = new Address("1512 Sun Ln", "San Jose", "CA",95132, "United States");
                Customer customer = new Customer("asas", 2648907, address);
                myCustomerRef.child("12341").setValue(customer);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // logic to display current logged in user info
        if (user.getUid() != null) {
            myCustomerRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Customer customer = dataSnapshot.getValue(Customer.class);
                    mCustomerTextView.setText("Logged in as: " + customer.getName());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

    // When button id = browsePostBtn clicked takes you to BrowsePostActivity
    public void toBrowsePost(View view) {
        Intent intent = new Intent(this, BrowsePostActivity.class);
        startActivity(intent);
    }

    // When button id = logoutBtn clicked logouts user and goes to LoginActivity
    public void toLogin(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void toNewPost(View view) {
        Intent intent = new Intent(this, NewPostActivity.class);
        startActivity(intent);
    }

}

package com.sjsu.jese.parkhere.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sjsu.jese.parkhere.MainActivity;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Customer;

public class CreateAccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference customersRef = mRootRef.child("Customers");

    private EditText emailText;
    private EditText passwordText;
    private EditText screenNameText;
    private EditText phoneNumText;
    private EditText stateText;
    private EditText streetAddressText;
    private EditText cityText;
    private EditText zipText;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailText = (EditText) findViewById(R.id.emailField);
        passwordText = (EditText) findViewById(R.id.passwordField);
        screenNameText = (EditText) findViewById(R.id.screenNameField);
        phoneNumText = (EditText) findViewById(R.id.phoneNumberField);
        stateText = (EditText) findViewById(R.id.stateField);
        streetAddressText = (EditText) findViewById(R.id.streetAddrField);
        cityText = (EditText) findViewById(R.id.cityField);
        zipText = (EditText) findViewById(R.id.zipField);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

    }
    private  boolean validateForm() {
        boolean valid = true;

        String email = emailText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailText.setError("Required.");
            valid = false;
        } else {
            emailText.setError(null);
        }

        String password = passwordText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            passwordText.setError("Required.");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        String phoneNum = phoneNumText.getText().toString();
        if (!android.util.Patterns.PHONE.matcher(phoneNum).matches()) {
            phoneNumText.setError("Invalid phone number.");
            valid = false;
        } else {
            phoneNumText.setError(null);
        }

        return valid;
    }

    public void createAccount() {
        if(!validateForm()) {
            return;
        }

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating account...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String screenName = screenNameText.getText().toString();
                    String phoneNum = phoneNumText.getText().toString();
                    String streetAddress = streetAddressText.getText().toString();
                    String city = cityText.getText().toString();
                    String state = stateText.getText().toString();
                    int zip = Integer.parseInt(zipText.getText().toString());

                    Customer customer = new Customer(screenName, phoneNum,
                            new Address(streetAddress,city,state,zip,"US"));

                    // add to Firebase database
                    customersRef.child(user.getUid()).setValue(customer);

                   // set values for screenname and photourl
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(screenName)
                            .setPhotoUri(Uri.parse(""))
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                    }
                                }
                            });

                    toLogin();
                }
                else {
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void toLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

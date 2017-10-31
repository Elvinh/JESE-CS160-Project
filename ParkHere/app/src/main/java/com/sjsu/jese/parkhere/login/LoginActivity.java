package com.sjsu.jese.parkhere.login;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.sjsu.jese.parkhere.CustomerDAO;
import com.sjsu.jese.parkhere.MainActivity;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Customer;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText emailField;
    private EditText passwordField;
    private TextView greetingTextView;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.emailText);
        passwordField = (EditText) findViewById(R.id.passwordText);
        greetingTextView = (TextView) findViewById(R.id.helloText);

        findViewById(R.id.signInBtn).setOnClickListener(this);
        findViewById(R.id.createAccountBtn).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            greetingTextView.setText("Hello " + currentUser.getDisplayName());
        }
        updateUI(currentUser);
    }

    private void createAccount(String email, String password) {
        if(!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Customer customer = new Customer("Koby", 69, new Address("Jane St","Milpitas","CA",95035,"US"));
                    CustomerDAO.getInstance().addCustomer(user.getUid(), customer);
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName("Dogger93")
                            .setPhotoUri(Uri.parse(""))
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User profile updated.");
                                    }
                                }
                            });
                    updateUI(user);

                }
        }
        });
    }
    private void signIn(String email, String password) {
        if(!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                           // If successful sign in, update UI with signed in user's info
                            Toast.makeText(LoginActivity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            toMain(user);
                        } else {
                            // if not successful, display message
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signOut() {
        if(mAuth.getCurrentUser() != null) {
            mAuth.signOut();
        } else {
            Toast.makeText(LoginActivity.this, "Not Logged in",
                    Toast.LENGTH_SHORT).show();
        }

        updateUI(mAuth.getCurrentUser());
    }
    private  boolean validateForm() {
        boolean valid = true;

        String email = emailField.getText().toString();
        if(TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            valid = false;
        } else {
            emailField.setError(null);
        }

        String password = passwordField.getText().toString();
        if(TextUtils.isEmpty(email)) {
            passwordField.setError("Required.");
            valid = false;
        } else {
            passwordField.setError(null);
        }

        return valid;
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {

        } else {
            findViewById(R.id.emailText).setVisibility(View.VISIBLE);
            findViewById(R.id.passwordText).setVisibility(View.VISIBLE);
            findViewById(R.id.signInBtn).setVisibility(View.VISIBLE);
        }
    }
    private void toMain(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.createAccountBtn) {
            createAccount(emailField.getText().toString(), passwordField.getText().toString());
        } else if(id == R.id.signInBtn) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        } else if(id == R.id.logoutBtn) {
            signOut();
        }
    }
}

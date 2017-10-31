package com.sjsu.jese.parkhere;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.emailText);
        passwordField = (EditText) findViewById(R.id.passwordText);

        findViewById(R.id.signInBtn).setOnClickListener(this);
        findViewById(R.id.createAccountBtn).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
        }
        });
    }
    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                           // If successful sign in, update UI with signed in user's info
                            Toast.makeText(LoginActivity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // if not successful, display message
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

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
        }
    }
}

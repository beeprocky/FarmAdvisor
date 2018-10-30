package com.project.farmadvisor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";

    private EditText edtEmail;
    private EditText edtPassword;

    private FirebaseAuth auth;

    private LinearLayout lohBtnSignIn;
    private LinearLayout lohBtnSignUp;
    private LinearLayout lohPgbSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize firebase auth
        auth = FirebaseAuth.getInstance();

        // user input holder
        edtEmail = findViewById(R.id.edtSignInEmail);
        edtPassword = findViewById(R.id.edtSignInPassword);

        // button and progress bar (can't change visibility for some reason)
        Button btnSignIn = findViewById(R.id.btnSignInSignIn);
        Button btnSignUp = findViewById(R.id.btnSignInSignUp);
//        ProgressBar pgbSignIn = findViewById(R.id.pgbSignInSignIn);

        // linear layout used to control visibility of button and progress bar
        lohBtnSignIn = findViewById(R.id.lohBtnSignInSignIn);
        lohBtnSignUp = findViewById(R.id.lohBtnSignInSignUp);
        lohPgbSignIn = findViewById(R.id.lohPgbSignInSignIn);

        // sign in button click listener
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get input from view
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                // hide button and show progress bar
                lohBtnSignIn.setVisibility(View.INVISIBLE);
                lohBtnSignUp.setVisibility(View.INVISIBLE);
                lohPgbSignIn.setVisibility(View.VISIBLE);

                if (email.isEmpty()) { // No email input
                    edtEmail.setHint(R.string.email_req_hint);
                    edtEmail.setHintTextColor(Color.RED);
                } else if (password.isEmpty()) { // No password input
                    edtPassword.setHint(R.string.password_req_hint);
                    edtPassword.setHintTextColor(Color.RED);
                } else { // everything set
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Log.e(TAG, Objects.requireNonNull(task.getException()).toString());
                                        Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        finish();
                                    }
                                }
                            });
                }
                // reset view visibility to normal state
                lohBtnSignIn.setVisibility(View.VISIBLE);
                lohBtnSignUp.setVisibility(View.VISIBLE);
                lohPgbSignIn.setVisibility(View.INVISIBLE);
            }
        });

        // sign up click listener
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Set button visibility
        lohBtnSignIn.setVisibility(View.VISIBLE);
        lohBtnSignUp.setVisibility(View.VISIBLE);
        lohPgbSignIn.setVisibility(View.INVISIBLE);
    }
}

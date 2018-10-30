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
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;

    private FirebaseAuth auth;

    private LinearLayout lohBtnSignUp;
    private LinearLayout lohPgbSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize firebase auth
        auth = FirebaseAuth.getInstance();

        // Input view
        edtName = findViewById(R.id.edtSignUpUserName);
        edtEmail = findViewById(R.id.edtSignUpEmail);
        edtPassword = findViewById(R.id.edtSignUpPassword);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        lohBtnSignUp = findViewById(R.id.lohBtnSignUp);
        lohPgbSignUp = findViewById(R.id.lohPgbSignUp);

        // Sign up click listener
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input from view
                final String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                //  Set layout visibility
                lohBtnSignUp.setVisibility(View.INVISIBLE);
                lohPgbSignUp.setVisibility(View.VISIBLE);

                // Inputs condition check
                if (name.isEmpty()) { // No name
                    edtName.setHint(R.string.user_name_req_hint);
                    edtName.setHintTextColor(Color.RED);
                } else if (email.isEmpty()) { // No email
                    edtEmail.setHint(R.string.email_req_hint);
                    edtEmail.setHintTextColor(Color.RED);
                } else if (password.isEmpty()) { //No password
                    edtPassword.setHint(R.string.password_req_hint);
                    edtPassword.setHintTextColor(Color.RED);
                } else if (name.length() < 3) { // Name is too short
                    Toast.makeText(SignUpActivity.this, R.string.user_name_too_short_hint, Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) { // Password is too short
                    Toast.makeText(SignUpActivity.this, R.string.password_too_short_hint, Toast.LENGTH_SHORT).show();
                } else { // Has email and password
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();
                                Objects.requireNonNull(auth.getCurrentUser()).updateProfile(profileChangeRequest);
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            } else {
                                Log.e(TAG, Objects.requireNonNull(task.getException()).toString());
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                // Reset layout visibility
                lohBtnSignUp.setVisibility(View.VISIBLE);
                lohPgbSignUp.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Set layout visibility
        lohBtnSignUp.setVisibility(View.VISIBLE);
        lohPgbSignUp.setVisibility(View.INVISIBLE);
    }
}

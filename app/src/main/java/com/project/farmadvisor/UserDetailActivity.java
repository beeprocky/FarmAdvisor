package com.project.farmadvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserDetailActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView txtName;
    private TextView txtEmail;
    private TextView txtFbid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        mAuth = FirebaseAuth.getInstance();

        txtName = findViewById(R.id.txtUserDetName);
        txtEmail = findViewById(R.id.txtUserDetEmail);
        txtFbid = findViewById(R.id.txtUserDetFbaseId);
        Button btnSignOut = findViewById(R.id.btnUserDetSignOut);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateUI(mAuth.getCurrentUser());
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            String name = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            String fbuid = currentUser.getUid();

            txtName.setText(name);
            txtEmail.setText(email);
            txtFbid.setText(fbuid);
        }
    }
}

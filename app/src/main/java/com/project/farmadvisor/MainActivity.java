package com.project.farmadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate View Object
        CardView cardUser = findViewById(R.id.cardMainUser);
        CardView cardFarm = findViewById(R.id.cardMainFarm);
        Button btnPreData = findViewById(R.id.btnMainPreData);

        // Set ClickListener on view object and use this instant as OnClickListener by implementation
        cardUser.setOnClickListener(this);
        cardFarm.setOnClickListener(this);
        btnPreData.setOnClickListener(this);

        // Initialize firebase
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        updateUI(mAuth.getCurrentUser());
    }

    @Override
    public void onClick(View v) {
        // Do something when user click(touch) on view
        switch (v.getId()) {
            // Clicked on user view group
            case R.id.cardMainUser:
                if (mAuth.getCurrentUser() != null) {
                    // Already sign in
                    Intent intent = new Intent(this, UserDetailActivity.class);
                    startActivity(intent);
                } else {
                    // Not sign in yet (user == null)
                    Intent intent = new Intent(this, SignInActivity.class);
                    startActivity(intent);
                }
                break;
            // Clicked on farm view group
            case R.id.cardMainFarm:
                // TODO
                Toast.makeText(MainActivity.this, "fCard", Toast.LENGTH_SHORT).show();
                break;
            // Clicked on 'Prepare Data' button
            case R.id.btnMainPreData:
                // TODO
                Toast.makeText(MainActivity.this, "toPD", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /*
    change UI based on current user
     */
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            TextView txtUsername = findViewById(R.id.txtMainUserName);
            txtUsername.setText(currentUser.getDisplayName());
        } else {
            TextView txtUserName = findViewById(R.id.txtMainUserName);
            txtUserName.setText(R.string.user_default_name);
            TextView txtFarmName = findViewById(R.id.txtMainFarmName);
            txtFarmName.setText(R.string.farm_default_name);
        }
    }
}

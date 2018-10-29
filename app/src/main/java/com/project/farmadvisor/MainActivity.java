package com.project.farmadvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView userCardView = findViewById(R.id.userCardView);
        CardView farmCardView = findViewById(R.id.farmCardView);
        Button mainToPreData = findViewById(R.id.mainToPreData);

        userCardView.setOnClickListener(this);
        farmCardView.setOnClickListener(this);
        mainToPreData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userCardView:
                Toast.makeText(MainActivity.this,"uCard",Toast.LENGTH_LONG).show();
                break;
            case R.id.farmCardView:
                Toast.makeText(MainActivity.this,"fCard",Toast.LENGTH_LONG).show();
                break;
            case R.id.mainToPreData:
                Toast.makeText(MainActivity.this,"toPD",Toast.LENGTH_LONG).show();
                break;
                default:
                    Toast.makeText(MainActivity.this,"what!",Toast.LENGTH_LONG).show();
        }
    }
}

package com.example.bigdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class DashBoard extends AppCompatActivity {
    RelativeLayout chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        chat =findViewById(R.id.chat);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PhoneNumberActivity.class));
                finish();

            }
        });
    }

    public void profile(View view) {
        startActivity(new Intent(getApplicationContext(),profile.class));
        finish();
    }
}
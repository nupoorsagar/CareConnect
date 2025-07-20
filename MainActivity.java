package com.example.healthsuppliesdonation;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Donate Supplies button
        findViewById(R.id.button_donate).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DonateSuppliesActivity.class);
            startActivity(intent);
        });

        // View Needs button
        findViewById(R.id.button_view_need).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ViewNeedsActivity.class);
            startActivity(intent);
        });

        // Track My Donations button
        findViewById(R.id.button_track).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TrackDonationsActivity.class);
            startActivity(intent);
        });

        // Request Supplies button
        findViewById(R.id.button_request).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RequestSuppliesActivity.class);
            startActivity(intent);
        });
    }
}

package com.example.healthsuppliesdonation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TrackDonationsActivity extends AppCompatActivity {

    private LinearLayout donationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_donations);

        donationListView = findViewById(R.id.donation_list_view);

        loadDonations();

        // Back button functionality
        findViewById(R.id.button_back).setOnClickListener(view -> {
            Intent intent = new Intent(TrackDonationsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Finish this activity
        });
    }

    private void loadDonations() {
        // Get the list of donations from the DataModel
        List<Donation> donations = DataModel.getInstance().getDonations();
        donationListView.removeAllViews();

        // Display each donation in the list
        for (Donation donation : donations) {
            TextView textView = new TextView(this);
            textView.setText("Hospital/NGO: " + donation.getHospitalName() + " | Supplies: " + donation.getSuppliesDonated());
            donationListView.addView(textView);
        }
    }
}

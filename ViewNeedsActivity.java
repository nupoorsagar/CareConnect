package com.example.healthsuppliesdonation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewNeedsActivity extends AppCompatActivity {

    private Spinner organizationSpinner;
    private Button contactButton, emailButton, backButton;
    private HashMap<String, Request> organizationMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_need);

        organizationSpinner = findViewById(R.id.organization_spinner);
        contactButton = findViewById(R.id.button_contact);
        emailButton = findViewById(R.id.button_email);
        backButton = findViewById(R.id.button_back);

        List<Request> requests = DataModel.getInstance().getRequests();
        List<String> organizationNames = new ArrayList<>();
        organizationMap = new HashMap<>();

        // Populate the organization names list and map each name to its corresponding Request object
        for (Request request : requests) {
            String organizationName = request.getHospitalName();
            organizationNames.add(organizationName);
            organizationMap.put(organizationName, request);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, organizationNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        organizationSpinner.setAdapter(adapter);

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedOrganization = (String) organizationSpinner.getSelectedItem();
                Request selectedRequest = organizationMap.get(selectedOrganization);
                if (selectedRequest != null) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + selectedRequest.getPhoneNumber()));
                    startActivity(callIntent);
                }
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedOrganization = (String) organizationSpinner.getSelectedItem();
                Request selectedRequest = organizationMap.get(selectedOrganization);
                if (selectedRequest != null) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:" + selectedRequest.getEmail()));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Donation of Supplies");
                    startActivity(emailIntent);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewNeedsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}


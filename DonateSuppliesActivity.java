package com.example.healthsuppliesdonation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class DonateSuppliesActivity extends AppCompatActivity {

    private AutoCompleteTextView suppliesDonatedAutoComplete;
    private AutoCompleteTextView organizationNameAutoComplete;
    private Button donateButton;
    private Button backButton;

    private Map<String, List<String>> organizationSuppliesMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_supplies);

        suppliesDonatedAutoComplete = findViewById(R.id.supplies_auto_complete);
        organizationNameAutoComplete = findViewById(R.id.hospital_name_auto_complete);
        donateButton = findViewById(R.id.donate_button);
        backButton = findViewById(R.id.button_back);

        // Populate AutoCompleteTextView for supplies
        updateSuppliesList();

        // Populate AutoCompleteTextView for organization names
        updateOrganizationsList();

        organizationNameAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Update supplies list based on selected organization
                String selectedOrganization = charSequence.toString().trim();
                updateSuppliesListForOrganization(selectedOrganization);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No action needed
            }
        });

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String suppliesDonated = suppliesDonatedAutoComplete.getText().toString().trim();
                String organizationName = organizationNameAutoComplete.getText().toString().trim();

                if (!suppliesDonated.isEmpty() && !organizationName.isEmpty()) {
                    Donation donation = new Donation(suppliesDonated, organizationName);
                    DataModel.getInstance().addDonation(donation);

                    // Show a toast message
                    String toastMessage = suppliesDonated + " was donated to " + organizationName;
                    Toast.makeText(DonateSuppliesActivity.this, toastMessage, Toast.LENGTH_LONG).show();

                    // Clear the input fields
                    suppliesDonatedAutoComplete.setText("");
                    organizationNameAutoComplete.setText("");
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();  // Finish the current activity and return to the previous one
            }
        });
    }

    private void updateSuppliesList() {
        List<String> suppliesList = DataModel.getInstance().getAllSuppliesRequested();
        ArrayAdapter<String> suppliesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, suppliesList);
        suppliesDonatedAutoComplete.setAdapter(suppliesAdapter);
    }

    private void updateOrganizationsList() {
        List<String> organizationsList = DataModel.getInstance().getAllOrganizationsRequested();
        ArrayAdapter<String> organizationsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, organizationsList);
        organizationNameAutoComplete.setAdapter(organizationsAdapter);

        // Populate the map for organization supplies
        populateOrganizationSuppliesMap();
    }

    private void updateSuppliesListForOrganization(String organization) {
        List<String> suppliesList = organizationSuppliesMap.get(organization);
        if (suppliesList != null) {
            ArrayAdapter<String> suppliesAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, suppliesList);
            suppliesDonatedAutoComplete.setAdapter(suppliesAdapter);
        } else {
            suppliesDonatedAutoComplete.setAdapter(null);
        }
    }

    private void populateOrganizationSuppliesMap() {
        for (Request request : DataModel.getInstance().getRequests()) {
            String organization = request.getHospitalName();
            String supply = request.getSuppliesNeeded();
            if (!organizationSuppliesMap.containsKey(organization)) {
                organizationSuppliesMap.put(organization, new ArrayList<>());
            }
     organizationSuppliesMap.get(organization).add(supply);
        }
    }
}

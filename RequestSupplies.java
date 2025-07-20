package com.example.healthsuppliesdonation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class RequestSuppliesActivity extends AppCompatActivity {

    private AutoCompleteTextView hospitalNameAutoComplete;
    private AutoCompleteTextView suppliesNeededAutoComplete;
    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private Button requestButton;

    // Sample data for AutoCompleteTextViews
    private static final List<String> ORGANIZATIONS = Arrays.asList(
            "Sanjivani Rural Hospital", "Shraddha Rural Healthcare Center",
            "Care Hospital", "City Medical Foundation"
    );

    private static final List<String> SUPPLIES = Arrays.asList(
            "Face Masks", "Gloves", "Hand Sanitizers", "Medicines",
            "Oxygen Cylinders", "Ventilators"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_supplies);

        hospitalNameAutoComplete = findViewById(R.id.hospital_name_auto_complete);
        suppliesNeededAutoComplete = findViewById(R.id.supplies_needed_auto_complete);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        requestButton = findViewById(R.id.request_button);

        // Set up AutoCompleteTextViews
        ArrayAdapter<String> hospitalAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, ORGANIZATIONS);
        hospitalNameAutoComplete.setAdapter(hospitalAdapter);

        ArrayAdapter<String> suppliesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, SUPPLIES);
        suppliesNeededAutoComplete.setAdapter(suppliesAdapter);

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hospitalName = hospitalNameAutoComplete.getText().toString().trim();
                String suppliesNeeded = suppliesNeededAutoComplete.getText().toString().trim();
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                if (!hospitalName.isEmpty() && !suppliesNeeded.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
                    Request request = new Request(hospitalName, suppliesNeeded, phoneNumber, email);
                    DataModel.getInstance().addRequest(request);

                    String toastMessage = suppliesNeeded + " added to needs list for " + hospitalName;
                    Toast.makeText(RequestSuppliesActivity.this, toastMessage, Toast.LENGTH_LONG).show();

                    hospitalNameAutoComplete.setText("");
                    suppliesNeededAutoComplete.setText("");
                    phoneNumberEditText.setText("");
                    emailEditText.setText("");
                }
            }
        });

        findViewById(R.id.button_back).setOnClickListener(view -> {
            Intent intent = new Intent(RequestSuppliesActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}

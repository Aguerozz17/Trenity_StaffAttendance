package com.example.trenity_staffattendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trenity_staffattendance.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Objects;

public class profile extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView nameTextView;
    private TextView emailTextView;

    private TextView staffIDTextView;

    private TextView departmentTextView;

    Button scan, logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Reference UI elements
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        staffIDTextView = findViewById(R.id.staffIDTextView);
        departmentTextView = findViewById(R.id.departmentTextView);

        // Example: Retrieve data from Firestore
        retrieveDataFromFirestore();

        scan = findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, qrCodeMainActivity.class));
            }
        });
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, LoginActivity.class));
            }
        });
    }

    private void retrieveDataFromFirestore() {
        db.collection("staff")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            // Each document in the "staff" collection

                            // Assuming you have "staffID", "department", "name", and "email" fields in your documents
                            String staffID = document.getString("staffID");
                            String department = document.getString("department");
                            String name = document.getString("name");
                            String email = document.getString("email");

                            // Example: Update TextViews with retrieved data
                            appendDataToTextView(staffID, department, name, email);
                        }
                    } else {
                        // Handle errors
                    }
                });
    }

    private void appendDataToTextView(String staffID, String department, String name, String email) {



        staffIDTextView.setText( staffID);
        departmentTextView.setText( department);
        nameTextView.setText(name);
        emailTextView.setText( email);
    }
}

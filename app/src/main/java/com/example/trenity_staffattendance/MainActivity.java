package com.example.trenity_staffattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
    }
}
package com.example.mousefinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;          // ← import

public class SignUpActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etConfirm;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // ———— SETUP TOOLBAR ————
        Toolbar toolbar = findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        // ———— FIND YOUR VIEWS ————
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirm  = findViewById(R.id.etConfirmPassword);
        btnCreate  = findViewById(R.id.btnCreateAccount);

        // … rest of your sign-up logic remains unchanged …
    }
}

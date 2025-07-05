package com.example.mousefinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up Toolbar with back arrow
        Toolbar toolbar = findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        // Enable the Up arrow
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // Find our views
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin   = findViewById(R.id.btnLogin);

        // Local username/password login
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString();

            SharedPreferences prefs = getSharedPreferences("user_db", MODE_PRIVATE);
            String saved = prefs.getString(username, null);

            if (saved != null && saved.equals(password)) {
                // Success → HomeActivity
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                i.putExtra("username", username);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(
                        LoginActivity.this,
                        "Username or password wrong",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}

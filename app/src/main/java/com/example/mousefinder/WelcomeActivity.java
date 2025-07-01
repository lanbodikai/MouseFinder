package com.example.mousefinder;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // “I’m New!” → SignUpActivity
        findViewById(R.id.btnNewUser).setOnClickListener(v ->
                startActivity(new Intent(WelcomeActivity.this, SignUpActivity.class))
        );

        // “I Already Have an Account” → LoginActivity
        findViewById(R.id.btnExistingUser).setOnClickListener(v ->
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class))
        );
    }
}

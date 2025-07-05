package com.example.mousefinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class WelcomeActivity extends AppCompatActivity {
    private GoogleSignInClient googleClient;
    private ActivityResultLauncher<Intent> googleLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_welcome);
        setSupportActionBar(toolbar);

        // Local username/password “Continue”
        EditText etUser = findViewById(R.id.etUsername);
        EditText etPass = findViewById(R.id.etPassword);
        Button btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(v -> {
            String u = etUser.getText().toString().trim();
            String p = etPass.getText().toString();
            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this,
                        "Please enter both username and password",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("user_db", MODE_PRIVATE);
            String stored = prefs.getString(u, null);
            if (stored == null) {
                // first-time: save and go Home
                prefs.edit().putString(u, p).apply();
                navigateHome(u);
            } else if (stored.equals(p)) {
                // correct password → Home
                navigateHome(u);
            } else {
                Toast.makeText(this,
                        "Username or password wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Google sign-in setup
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleClient = GoogleSignIn.getClient(this, gso);

        googleLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    if (result.getResultCode() == RESULT_OK && data != null) {
                        try {
                            Task<GoogleSignInAccount> task =
                                    GoogleSignIn.getSignedInAccountFromIntent(data);
                            GoogleSignInAccount acct = task.getResult(ApiException.class);
                            String email = acct.getEmail();

                            SharedPreferences prefs =
                                    getSharedPreferences("user_db", MODE_PRIVATE);
                            if (!prefs.contains(email)) {
                                // first-time Google: save empty password
                                prefs.edit().putString(email, "").apply();
                            }
                            navigateHome(email);
                        } catch (ApiException e) {
                            Toast.makeText(this,
                                    "Google sign-in failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        SignInButton btnGoogle = findViewById(R.id.btnGoogleSignIn);
        btnGoogle.setSize(SignInButton.SIZE_ICON_ONLY);
        btnGoogle.setColorScheme(SignInButton.COLOR_DARK);
        btnGoogle.setOnClickListener(v ->
                googleLauncher.launch(googleClient.getSignInIntent())
        );
    }

    private void navigateHome(String username) {
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("username", username);
        startActivity(i);
        finish();
    }
}

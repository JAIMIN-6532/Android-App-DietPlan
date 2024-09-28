package com.example.dietapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dietapp.Database.Callback.FindByModel;
import com.example.dietapp.Database.DataManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmailLogin, editTextPasswordLogin;
    Button buttonLogin;
    TextView textViewRegister;

    // Firebase authentication instance
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        editTextEmailLogin = findViewById(R.id.editTextEmailLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewRegister = findViewById(R.id.textViewRegister);

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmailLogin.getText().toString().trim();
            String password = editTextPasswordLogin.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(LoginActivity.this, "Email field is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Password field is empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if login credentials are correct
            DataManager.CheckLoginCredentials(email, password, new FindByModel() {
                @Override
                public void onSuccess(Object model) {
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                    // Retrieve first login flag from SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("DietAppPrefs", MODE_PRIVATE);
                    boolean isFirstLogin = sharedPreferences.getBoolean("isFirstLogin", true);

                    if (isFirstLogin) {
                        // Set the first login flag to false after the first login
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isFirstLogin", false);
                        editor.apply();

                        // Navigate to UserDetailsActivity for first-time login
                        Intent intent = new Intent(LoginActivity.this, UserDetailsActivity.class);
                        startActivity(intent);
                    } else {
                        // Navigate to GoalSelectionActivity for subsequent logins
                        Intent intent = new Intent(LoginActivity.this, GoalSelectionActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }

                @Override
                public void onFailure() {
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials, Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            });
        });


        textViewRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}

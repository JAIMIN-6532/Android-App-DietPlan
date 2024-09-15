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

            DataManager.FindByGmail(email, password, new FindByModel() {
                @Override
                public void onSuccess(Object model) {
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, UserDetailsActivity.class);
                    startActivity(intent);
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

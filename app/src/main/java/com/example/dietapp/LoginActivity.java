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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dietapp.Database.Callback.FindByModel;
import com.example.dietapp.Database.DataManager;
import com.example.dietapp.Database.DataModel.User;
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

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        Toast.makeText(LoginActivity.this, "Login SuccesFull!..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,UserDetailsActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(LoginActivity.this, "please Enter Correct Cradentials!!", Toast.LENGTH_SHORT).show();
                    }
                });


                // Sign in with Firebase
//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(LoginActivity.this, task -> {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(LoginActivity.this, UserDetailsActivity.class);
//                                startActivity(intent);
//                                finish();
//                            } else {
//                                // If sign in fails, display a message to the user
//                                Toast.makeText(LoginActivity.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }
        });

        textViewRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
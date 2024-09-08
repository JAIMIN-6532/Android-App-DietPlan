package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dietapp.Database.Callback.InsertModel;
import com.example.dietapp.Database.DataManager;
import com.example.dietapp.Database.DataModel.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword;
    Button buttonRegister;
    TextView textViewLogin;

    // Firebase authentication instance
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewLogin = findViewById(R.id.textViewLogin);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String Username = editTextUsername.getText().toString().trim();

                if (TextUtils.isEmpty(Username)) {
                    Toast.makeText(RegisterActivity.this, "name field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Email field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Password field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(Username,email,password);

                DataManager.InsertData(user, new InsertModel() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(RegisterActivity.this, "Registration Faild", Toast.LENGTH_SHORT).show();
                    }
                });

                // Register new user with Firebase

//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(RegisterActivity.this, task -> {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                startActivity(intent);
//                                finish();
//                            } else {
//                                // If sign in fails, display a message to the user
//                                Toast.makeText(RegisterActivity.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }
        });

        textViewLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
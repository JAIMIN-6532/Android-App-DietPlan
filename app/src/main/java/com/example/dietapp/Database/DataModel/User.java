package com.example.dietapp.Database.DataModel;

import androidx.annotation.NonNull;

public class User {
    private String key;
    private String name;
    private String email;
    private String password;
    private boolean formSubmit;
    private UserDetails userDetails;  // Embed UserDetails as part of the User

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.formSubmit = false;
        this.userDetails = new UserDetails();  // Initialize with empty UserDetails
    }

    // Getters and Setters
    public boolean isFormSubmit() {
        return formSubmit;
    }

    public void setFormSubmit(boolean formSubmit) {
        this.formSubmit = formSubmit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Password='" + password + '\'' +
                ", UserDetails=" + userDetails.toString() +
                '}';
    }
}


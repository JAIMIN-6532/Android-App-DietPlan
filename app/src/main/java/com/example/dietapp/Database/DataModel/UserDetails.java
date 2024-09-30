package com.example.dietapp.Database.DataModel;

public class UserDetails {
    private String name;
    private String age;
    private String weight;
    private String height;
    private String gender;
    private String targetWeight;
    private String userkey;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    private String days;

    // Empty constructor needed for Firebase
    public UserDetails() {
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    // Constructor with parameters
    public UserDetails(String name, String age, String weight, String height, String gender, String targetWeight,String userkey) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.targetWeight = targetWeight;
        this.userkey = userkey;
    }

    // Getters and setters for each field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(String targetWeight) {
        this.targetWeight = targetWeight;
    }
}

package com.example.dietapp.Database.DataModel;

public class UserDetails {
    private String key;
    private String name;
    private String age;
    private String weight;
    private String height;
    private String gender;
    private String targetWeight;
    private String userkey;
    private int days;
    //private int months;

    // Empty constructor needed for Firebase
    public UserDetails() {
    }

//    public int getMonths() {
//        return months;
//    }
//
//    public void setMonths(int months) {
//        this.months = months;
//    }

    // Constructor with parameters
    public UserDetails(String name, String age, String weight, String height, String gender, String targetWeight, String userkey,int days) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.targetWeight = targetWeight;
        this.userkey = userkey;
        this.days = days;  // Default value for days
//        this.months= months;

    }

    // Getters and setters for each field
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

package com.example.dietapp.Database.DataModel;

public class UserDetails {
    private String name;
    private String age;
    private String weight;
    private String height;
    private String gender;
    private String targetWeight;
    private int days;
    private String userkey;

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Empty constructor needed for Firebase
    public UserDetails() {
    }

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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "Age='" + age + '\'' +
                ", Weight='" + weight + '\'' +
                ", Height='" + height + '\'' +
                ", Gender='" + gender + '\'' +
                ", TargetWeight='" + targetWeight + '\'' +
                ", Days=" + days +
                '}';
    }
}

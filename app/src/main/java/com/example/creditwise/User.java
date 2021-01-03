package com.example.creditwise;

public class User {
    public static int points = 0;
    public static int userId = -1;

    public String firstName;
    public String lastName;
    public String email;
    public String uid;

    public User() {
    }

    public User(String firstName, String lastName, String email, String uid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

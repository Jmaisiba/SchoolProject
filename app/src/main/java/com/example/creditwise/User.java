package com.example.creditwise;

public class User {
    public static int points = 0;
    public static int userId = -1;

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}

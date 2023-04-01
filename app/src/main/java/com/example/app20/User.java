package com.example.app20;

public class User {
    private  String email;
    private  String key;

    private  String Password;

    public User(String email, String key, String password) {
        this.email = email;
        this.key = key;
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

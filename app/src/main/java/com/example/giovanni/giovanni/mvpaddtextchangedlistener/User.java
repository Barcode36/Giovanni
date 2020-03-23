package com.example.giovanni.giovanni.mvpaddtextchangedlistener;

public class User {

    private String username = "";
    private String email = "";

    User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        if (email.equals("") && username.equals("")) {
            return "";
        }
        if (email.equals("") && !username.equals("")) {
            return "Username: " + username;
        }
        if (!email.equals("") && username.equals("")) {
            return "Email: " + email;
        }
        else {
            return "Email: " + getEmail() + "\nUsername: " + getUsername();
        }
    }
}

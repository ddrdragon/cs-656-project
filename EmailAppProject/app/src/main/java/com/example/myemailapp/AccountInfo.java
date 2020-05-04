package com.example.myemailapp;

public class AccountInfo {
    private String username, password;
    private String address, subject, message;

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setAddress(String address)   { this.address = address; }

    public void setSubject(String subject)   { this.subject = subject; }

    public void setMessage(String message)   { this.message = message; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress()  { return address; }

    public String getSubject()  { return subject; }

    public String getMessage()  { return message; }
}
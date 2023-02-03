package com.wuriyanto.example.application.entity;

public class RegisterResponse {
    
    private String fullName;
    private String email;

    public RegisterResponse(User user) {
        this.fullName = user.getFullName();
        this.email = user.getEmail();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}

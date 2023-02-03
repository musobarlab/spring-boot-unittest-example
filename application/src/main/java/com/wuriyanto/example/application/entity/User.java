package com.wuriyanto.example.application.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractJpaEntity {
    
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    public User() {

    }

    public User(String fullName, String email, Date createdAt, Date updatedAt) {
        super(createdAt, updatedAt);
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

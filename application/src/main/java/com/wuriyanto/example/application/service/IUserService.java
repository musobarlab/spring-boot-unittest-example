package com.wuriyanto.example.application.service;

import com.wuriyanto.example.application.entity.RegisterRequest;
import com.wuriyanto.example.application.entity.User;

public interface IUserService {

    public User register(RegisterRequest userRequest);

    public User getUser(String email);

}

package com.wuriyanto.example.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuriyanto.example.application.CustomResponse;
import com.wuriyanto.example.application.EmptyJson;
import com.wuriyanto.example.application.entity.RegisterRequest;
import com.wuriyanto.example.application.entity.RegisterResponse;
import com.wuriyanto.example.application.entity.User;
import com.wuriyanto.example.application.service.IUserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @PostMapping("")
    public CustomResponse registerUser(@RequestBody RegisterRequest registerRequest, HttpServletResponse response) {
        try {
            User newUser = userService.register(registerRequest);

            return new CustomResponse(HttpStatus.OK.value(),
                    true,
                    new RegisterResponse(newUser),
                    "register user succeed");
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new CustomResponse(HttpStatus.BAD_REQUEST.value(),
                    false,
                    new EmptyJson(),
                    e.getMessage());

        }
    }

    @GetMapping("")
    public CustomResponse getUser(@RequestParam(value = "email") String email, HttpServletResponse response) {
        if (email == null || email.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new CustomResponse(HttpStatus.BAD_REQUEST.value(),
                    false,
                    new EmptyJson(),
                    "email query param cannot be empty");
        }

        try {
            User user = userService.getUser(email);

            return new CustomResponse(HttpStatus.OK.value(),
                    true,
                    new RegisterResponse(user),
                    "get user succeed");
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new CustomResponse(HttpStatus.BAD_REQUEST.value(),
                    false,
                    new EmptyJson(),
                    e.getMessage());

        }
    }
}

package com.ga.spring_hw.controller;

import com.ga.spring_hw.model.User;
import com.ga.spring_hw.model.request.LoginRequest;
import com.ga.spring_hw.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path  = "/auth/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("Calling Service register ==> ");
        return userService.register(userObject);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest ){
        System.out.println("Calling Service loginUser ==> ");
        return userService.loginUser(loginRequest);
    }

}

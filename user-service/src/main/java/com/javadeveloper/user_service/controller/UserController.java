package com.javadeveloper.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javadeveloper.user_service.model.Users;
import com.javadeveloper.user_service.service.UserService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/users-details")
public class UserController {

    

    @Autowired
    private UserService userService;


    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/addUser")
    public Users save(@RequestBody Users entity) {
        return userService.save(entity);
    }

    @PostMapping("/generateToken")
    public String generateJWTToken(@RequestBody Users users) {
        return userService.generateJWTTokenForUser(users);
    }
}

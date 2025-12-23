package com.javadeveloper.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javadeveloper.user_service.model.Users;
import com.javadeveloper.user_service.service.MyUserDetailsService;
import com.javadeveloper.user_service.service.UserService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/users-details")
public class UserController {

    

    @Autowired
    private UserService userService;

    @Autowired
    MyUserDetailsService myUserDetailsService;


    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/{userName}")
    public UserDetails getUserByUserName(@RequestParam String userName) {
        return myUserDetailsService.loadUserByUsername(userName);
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

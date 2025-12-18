package com.javadeveloper.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.javadeveloper.user_service.model.Users;
import com.javadeveloper.user_service.repository.UserRepository;

@Service
public class UserService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


     public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

     public Users getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public Users save(Users user) {
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        userRepository.save(user);
       return user;
    }

    public String generateJWTTokenForUser(Users users) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(users.getUserName(), users.getUserPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(users.getUserName());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
    
}

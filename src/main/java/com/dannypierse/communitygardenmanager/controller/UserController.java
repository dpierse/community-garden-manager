package com.dannypierse.communitygardenmanager.controller;

import com.dannypierse.communitygardenmanager.model.User;
import com.dannypierse.communitygardenmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dannypierse.communitygardenmanager.dto.LoginRequest;
import com.dannypierse.communitygardenmanager.dto.LoginResponse;
import com.dannypierse.communitygardenmanager.security.JwtService;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {

        System.out.println("DEBUG USER:");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Role: " + user.getRole());

        return userService.saveUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/test")
    public String test() {
        return "Controller is working";
    }

    @GetMapping("/create-test-user")
    public String createTestUser() {

        User user = new User();
        user.setName("Danny");
        user.setEmail("danny@test.com");
        user.setPassword("1234");
        user.setRole("USER");

        userService.saveUser(user);

        return "User created successfully";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userService.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getEmail());
        System.out.println("TOKEN: " + token);

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                token
        );
    }
}
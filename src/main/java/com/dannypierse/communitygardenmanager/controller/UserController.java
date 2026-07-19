package com.dannypierse.communitygardenmanager.controller;

import com.dannypierse.communitygardenmanager.model.User;
import com.dannypierse.communitygardenmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
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
}
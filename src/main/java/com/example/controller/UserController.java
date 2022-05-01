package com.example.controller;

import com.example.entity.User;
import com.example.model.UserModel;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    @GetMapping("/get-all")
    public List<User> getAll() {
        return userService.getAll();
    }
}

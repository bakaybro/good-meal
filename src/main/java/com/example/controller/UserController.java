package com.example.controller;

import com.example.entity.User;
import com.example.model.UserAuthModel;
import com.example.model.UserModel;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public UserModel create(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    @GetMapping("/get-all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserModel getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public UserModel update(@PathVariable("id") Long id, @RequestBody UserModel userModel) {
        return userService.update(id, userModel);
    }

    @DeleteMapping("/delete/{id}")
    public UserModel deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/get-current")
    public User getCurrent() {
        return userService.getCurrentUser();
    }

    @PostMapping("/sign-in")
    public ResponseMessage<String> getAuthHeader(@RequestBody UserAuthModel userAuthModel) {
        return userService.getBasicAuthHeaderByAuthModel(userAuthModel);
    }
}

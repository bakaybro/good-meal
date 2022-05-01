package com.example.service;

import com.example.entity.User;
import com.example.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel createUser(UserModel userModel);
    List<User> getAll();
}

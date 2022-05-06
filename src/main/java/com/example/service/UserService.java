package com.example.service;

import com.example.entity.User;
import com.example.model.UserAuthModel;
import com.example.model.UserModel;
import com.example.util.ResponseMessage;

import java.util.List;

public interface UserService {
    UserModel createUser(UserModel userModel);
    List<UserModel> getAll();
    UserModel getById(Long id);
    UserModel update(Long id, UserModel userModel);
    UserModel deleteById(Long id);
    User getCurrentUser();
    ResponseMessage<String> getBasicAuthHeaderByAuthModel(UserAuthModel userAuthModel);
}

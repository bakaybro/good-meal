package com.example.service.impl;

import com.example.converter.UserConverter;
import com.example.entity.User;
import com.example.model.UserModel;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserModel createUser(UserModel userModel) {
        userRepository.save(userConverter.convertFromModel(userModel));
        return userModel;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}

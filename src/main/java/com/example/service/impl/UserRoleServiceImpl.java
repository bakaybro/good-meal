package com.example.service.impl;

import com.example.converter.UserRoleConverter;
import com.example.exceptions.ApiException;
import com.example.model.UserRoleModel;
import com.example.repository.UserRoleRepository;
import com.example.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRoleConverter userRoleConverter;

    @Override
    public UserRoleModel create(UserRoleModel userRoleModel) {
        if (userRoleModel.getRoleName().isEmpty()) throw new ApiException("Enter the name of the role", HttpStatus.BAD_REQUEST);
        userRoleRepository.save(userRoleConverter.convertFromModel(userRoleModel));
        return userRoleModel;
    }

    @Override
    public UserRoleModel getById(Long id) {
        return null;
    }

    @Override
    public List<UserRoleModel> getAll() {
        return null;
    }

    @Override
    public UserRoleModel update(UserRoleModel userRoleModel) {
        return null;
    }

    @Override
    public UserRoleModel delete(Long id) {
        return null;
    }
}

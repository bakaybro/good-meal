package com.example.service;

import com.example.entity.UserRole;
import com.example.model.UserRoleModel;

import java.util.List;

public interface UserRoleService {
    UserRoleModel create(UserRoleModel userRoleModel);
    UserRoleModel getById(Long id);
    List<UserRoleModel> getAll();
    UserRoleModel update(Long id, UserRoleModel userRoleModel);
    UserRoleModel deleteById(Long id);
}

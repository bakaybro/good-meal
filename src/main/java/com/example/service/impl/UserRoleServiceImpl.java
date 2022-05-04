package com.example.service.impl;

import com.example.converter.UserRoleConverter;
import com.example.entity.UserRole;
import com.example.exceptions.ApiException;
import com.example.model.UserRoleModel;
import com.example.repository.UserRoleRepository;
import com.example.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRoleConverter userRoleConverter;

    @Override
    public UserRoleModel create(UserRoleModel userRoleModel) {
        if (userRoleModel.getRoleName().isEmpty()) throw new ApiException("Enter the name of the ROLE", HttpStatus.BAD_REQUEST);
        userRoleRepository.save(userRoleConverter.convertFromModel(userRoleModel));
        return userRoleModel;
    }

    @Override
    public UserRoleModel getById(Long id) {
        return userRoleConverter.convertFromEntity(userRoleRepository.findById(id)
                .orElseThrow( () -> new ApiException("Didn't find a ROLE under id: " + id, HttpStatus.BAD_REQUEST)));
    }

    @Override
    public List<UserRoleModel> getAll() {
        List<UserRoleModel> userRoleModels = new ArrayList<>();
        for (UserRole userRole : userRoleRepository.findAll()) {
            userRoleModels.add(userRoleConverter.convertFromEntity(userRole));
        }
        if (userRoleModels.isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return userRoleModels;
    }

    @Override
    public UserRoleModel update(Long id, UserRoleModel userRoleModel) {
        UserRole userRoleForUpdate = userRoleConverter.convertFromModel(userRoleModel);
        if (userRoleModel != null) userRoleForUpdate.setRoleName(userRoleModel.getRoleName());
        userRoleForUpdate.setId(id);

        userRoleRepository.save(userRoleForUpdate);

        return userRoleConverter.convertFromEntity(userRoleForUpdate);
    }

    @Override
    public UserRoleModel deleteById(Long id) {
        UserRoleModel userRoleModelForDelete = getById(id);
        if (userRoleModelForDelete == null) throw new ApiException("Did not find the ROLE under the id to delete", HttpStatus.BAD_REQUEST);
        else userRoleRepository.delete(userRoleConverter.convertFromModel(userRoleModelForDelete));
        return userRoleModelForDelete;
    }
}

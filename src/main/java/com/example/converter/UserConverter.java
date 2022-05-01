package com.example.converter;

import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConverter<UserModel, User>{

    public UserConverter() {
        super(UserConverter::convertToEntity, UserConverter::convertToModel);
    }

    private static UserModel convertToModel(User entityToConvert) {
        if (entityToConvert == null) return null;

        return UserModel.builder()
                .login(entityToConvert.getLogin())
                .password(entityToConvert.getPassword())
                .email(entityToConvert.getEmail())
                .isActive(entityToConvert.getIsActive())
                .roleId(entityToConvert.getUserRole().getId())
                .build();
    }

    private static User convertToEntity(UserModel modelToConvert) {
        if (modelToConvert == null) return null;

        User userForReturn = new User();
        userForReturn.setLogin(modelToConvert.getLogin());
        userForReturn.setPassword(modelToConvert.getPassword());
        userForReturn.setEmail(modelToConvert.getEmail());
        userForReturn.setIsActive(modelToConvert.getIsActive());
        UserRole userRole = new UserRole();
        userRole.setId(modelToConvert.getRoleId());
        userForReturn.setUserRole(userRole);

        return userForReturn;
    }
}

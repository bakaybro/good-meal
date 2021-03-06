package com.example.service.impl;

import com.example.converter.UserConverter;
import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.exceptions.ApiException;
import com.example.model.UserAuthModel;
import com.example.model.UserModel;
import com.example.model.UserRoleModel;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_LOGIN_REGEX =
            Pattern.compile("^[а-яa-zА-ЯA-Z0-9._-]{3,}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserModel createUser(UserModel userModel) {
        Matcher email = VALID_EMAIL_ADDRESS_REGEX.matcher(userModel.getEmail());
        Matcher password = VALID_PASSWORD_REGEX.matcher(userModel.getPassword());
        Matcher login = VALID_LOGIN_REGEX.matcher(userModel.getLogin());
        if (!login.find()) throw new ApiException("Login is too short", HttpStatus.BAD_REQUEST);
        if (!password.find()) throw new ApiException("The password must contain alphanumeric and numeric characters, must not be less than 8 characters, and must not have spaces.", HttpStatus.BAD_REQUEST);
        if (!email.find()) throw new ApiException("Enter the correct email", HttpStatus.BAD_REQUEST);
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        userModel.setRoleId(2L);
        userModel.setIsActive(1L);
        userRepository.save(userConverter.convertFromModel(userModel));
        return userModel;
    }

    @Override
    public List<UserModel> getAll() {
        List<UserModel> userModels = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userModels.add(userConverter.convertFromEntity(user));
        }
        if (userModels.isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return userModels;
    }

    @Override
    public UserModel getById(Long id) {
        UserModel userModel = userConverter.convertFromEntity(userRepository.findById(id).orElse(null));
        if (userModel == null) throw new ApiException("Didn't find a user under id: " + id, HttpStatus.BAD_REQUEST);
        return userModel;
    }

    @Override
    public UserModel update(Long id, UserModel userModel) {
        User userForUpdate = userConverter.convertFromModel(userModel);

        userForUpdate.setId(id);
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        userForUpdate.setPassword(encodedPassword);
        userRepository.save(userForUpdate);
        return userConverter.convertFromEntity(userForUpdate);

    }

    @Override
    public UserModel deleteById(Long id) {
        UserModel userModelForUpdate = userConverter.convertFromEntity(userRepository.findById(id)
                .orElseThrow( () -> new ApiException("Did not find the USER under the id to delete. ID: " + id, HttpStatus.BAD_REQUEST)));

        userRepository.deleteById(id);
        return userModelForUpdate;
    }

    @Override
    public User getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getByLogin(login).orElse(null);
        if (user == null) throw new ApiException("There is no such USER", HttpStatus.BAD_REQUEST);
        return user;
    }

    @Override
    public ResponseMessage<String> getBasicAuthHeaderByAuthModel(UserAuthModel userAuthModel) {
        User user = userRepository.getByLogin(userAuthModel.getLogin())
                .orElseThrow( () -> new ApiException("The login is incorrect", HttpStatus.BAD_REQUEST));

        String encryptedPasswordDatabase = user.getPassword();
        boolean isPasswordCorrect = passwordEncoder.matches(userAuthModel.getPassword(), encryptedPasswordDatabase);
        if (!isPasswordCorrect) throw new ApiException("The password is incorrect", HttpStatus.BAD_REQUEST);

        String loginAndPassword = userAuthModel.getLogin() + " : " + userAuthModel.getPassword();
        String authorizationHeader = new String(Base64.getEncoder().encode(loginAndPassword.getBytes()));
        ResponseMessage<String> responseMessage = new ResponseMessage<>();

        return responseMessage.prepareSuccessMessage(authorizationHeader);
    }
}

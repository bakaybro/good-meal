package com.example.boot;

import com.example.model.UserModel;
import com.example.model.UserRoleModel;
import com.example.service.UserRoleService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements CommandLineRunner {

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        UserRoleModel adminRole = new UserRoleModel();
//        adminRole.setRoleName("ROLE_ADMIN");
//        userRoleService.create(adminRole);
//
//        UserRoleModel clientRole = new UserRoleModel();
//        clientRole.setRoleName("ROLE_CLIENT");
//        userRoleService.create(clientRole);


    }
}

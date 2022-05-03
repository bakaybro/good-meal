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

    @Autowired(required = false)
    UserRoleService userRoleService;

    @Autowired(required = false)
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        UserRoleModel clientRole = new UserRoleModel();
//        clientRole.setRoleName("ROLE_CLIENT");
//        userRoleService.create(clientRole);
//
//        UserRoleModel adminRole = new UserRoleModel();
//        adminRole.setRoleName("ROLE_ADMIN");
//        userRoleService.create(adminRole);
//
//        UserModel admin = new UserModel();
//        admin.setLogin("admin");
//        admin.setPassword("admin1717");
//        admin.setEmail("admin@gmail.com");
//        admin.setIsActive(1L);
//        admin.setRoleId(1L);
//        userService.createUser(admin);

    }
}

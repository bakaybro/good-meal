package com.example.controller;

import com.example.converter.UserRoleConverter;
import com.example.entity.UserRole;
import com.example.model.UserRoleModel;
import com.example.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-role")
public class RoleController {

    @Autowired
    UserRoleService userRoleService;

    @PostMapping("/create")
    public UserRoleModel create(@RequestBody UserRoleModel userRoleModel) {
        return userRoleService.create(userRoleModel);
    }

    @GetMapping("/{id}")
    public UserRoleModel getById(@PathVariable Long id) {
        return userRoleService.getById(id);
    }

    @GetMapping("/get-all")
    public List<UserRoleModel> getAll() {
        return userRoleService.getAll();
    }

    @PutMapping(value = "{id}")
    public UserRoleModel update(@PathVariable("id") Long id, @RequestBody UserRoleModel userRoleModel) {
        return userRoleService.update(id, userRoleModel);
    }

    @DeleteMapping("/{id}")
    public UserRoleModel deleteById(@PathVariable Long id) {
        return userRoleService.deleteById(id);
    }
}

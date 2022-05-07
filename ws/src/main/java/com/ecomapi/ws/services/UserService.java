package com.ecomapi.ws.services;


import com.ecomapi.ws.entities.RoleEntity;
import com.ecomapi.ws.entities.UserEntity;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity roleName);
    void addRoleToUser(String username,String role);
    UserEntity getUser(String username);
}

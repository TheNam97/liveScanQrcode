package com.crud_restfulapi.securityy.service;

import com.crud_restfulapi.model.User;
import com.crud_restfulapi.service.IGeneralService;
//import com.example.officebuilding.securityy.entities.User;
//import com.example.officebuilding.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);
    void CreateUser(User user);
}

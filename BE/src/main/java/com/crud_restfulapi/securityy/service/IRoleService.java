package com.crud_restfulapi.securityy.service;

import com.crud_restfulapi.model.Role;
import com.crud_restfulapi.service.IGeneralService;

import java.util.Optional;
//import com.example.officebuilding.securityy.entities.Role;
//import com.example.officebuilding.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByName(String name);
}
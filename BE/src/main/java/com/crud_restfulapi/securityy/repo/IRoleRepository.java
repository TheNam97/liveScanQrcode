package com.crud_restfulapi.securityy.repo;


import com.crud_restfulapi.model.Role;
//import com.example.officebuilding.securityy.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}

package com.crud_restfulapi.securityy.repo;

import com.crud_restfulapi.model.User;
//import com.example.officebuilding.securityy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}

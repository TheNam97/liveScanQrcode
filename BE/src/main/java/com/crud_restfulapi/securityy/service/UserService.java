package com.crud_restfulapi.securityy.service;

import com.crud_restfulapi.model.Role;
import com.crud_restfulapi.model.User;
import com.crud_restfulapi.securityy.UserPrinciple;
import com.crud_restfulapi.securityy.repo.IRoleRepository;
import com.crud_restfulapi.securityy.repo.IUserRepository;
//import com.example.officebuilding.securityy.UserPrinciple;
//import com.example.officebuilding.securityy.entities.User;
//import com.example.officebuilding.securityy.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleRepository iRoleRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void CreateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = iRoleRepository.findByName("ROLE_ADMIN").get();
        Set<Role> roleEntities = new HashSet<>();
        roleEntities.add(role);
        user.setRoles(roleEntities);
        userRepository.save(user);
    }
}

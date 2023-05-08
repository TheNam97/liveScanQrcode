package com.crud_restfulapi.securityy.controller;


import com.crud_restfulapi.model.User;
import com.crud_restfulapi.securityy.jwt.JwtResponse;
import com.crud_restfulapi.securityy.jwt.JwtService;
import com.crud_restfulapi.securityy.service.IUserService;
//import com.example.officebuilding.securityy.entities.User;
//import com.example.officebuilding.securityy.jwt.JwtResponse;
//import com.example.officebuilding.securityy.jwt.JwtService;
//import com.example.officebuilding.securityy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateTokenLogin(authentication);

            return ResponseEntity.ok(new JwtResponse(token));

//            String jwt = jwtService.generateTokenLogin(authentication);
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            User currentUser = userService.findByUsername(user.getUsername()).get();
//            return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi đăng nhập");
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.CreateUser(user);
        return new ResponseEntity<>("User register successfully!", HttpStatus.OK);
    }
}

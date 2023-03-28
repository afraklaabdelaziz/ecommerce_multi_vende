package com.ecommerce.ecommerce_multi_vende.controller;

import com.ecommerce.ecommerce_multi_vende.config.JwtUtils;
import com.ecommerce.ecommerce_multi_vende.dto.AuthenticationRequest;
import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import com.ecommerce.ecommerce_multi_vende.services.UserServices;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/authenticate")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserServices userService;
    private JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserServices userService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
public ResponseDto register(@Valid @RequestBody UserApp user){
    return userService.addUser(user);
}

@PostMapping("/login")
    public ResponseDto auth(@RequestBody AuthenticationRequest request){
     UserDetails user = ((UserDetails) userService.findByEmail(request.getEmail()).getData());
     if (user != null){
         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
         SecurityContextHolder.getContext().setAuthentication(authentication);
         return new ResponseDto("success","token",jwtUtils.generateToken(user));
     }
     return new ResponseDto("bad request","mot de passe ou email est incorrect");
    }
}

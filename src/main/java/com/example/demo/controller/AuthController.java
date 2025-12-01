package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserLoginResponse;
import com.example.demo.mapper.UserLoginMapper;
import com.example.demo.model.AppUser;
import com.example.demo.service.AppUserService;
import com.example.demo.service.JwtService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;
    private final JwtService jwtService;

    @PostMapping("/signin")
    public ResponseEntity<UserLoginResponse> authenticate(@RequestBody UserLoginRequest request) {

        // Verificar credenciales
        AppUser user = appUserService.authenticate(request);

        // Convertir a DTO seguro
        UserLoginResponse response = UserLoginMapper.toResponse(user);

        // Generar token
        String token = jwtService.generateToken(user);

        response.setToken(token);
        response.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(response);
    }
}

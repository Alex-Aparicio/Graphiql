package com.example.demo.mapper;

import com.example.demo.dto.UserLoginResponse;
import com.example.demo.model.AppUser;

public final class UserLoginMapper {

    public static UserLoginResponse toResponse(AppUser user) {
        if (user == null) return null;

        return UserLoginResponse.builder()
                .user(user.getUsername())
                .role(user.getRol().getName())
                .build();
    }
}

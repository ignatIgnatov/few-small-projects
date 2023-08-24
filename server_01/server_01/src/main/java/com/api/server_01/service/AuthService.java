package com.api.server_01.service;

import com.api.server_01.entity.UserEntity;
import com.api.server_01.entity.dto.AuthRequestDto;
import com.api.server_01.entity.dto.AuthResponseDto;
import com.api.server_01.entity.dto.RegisterRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto registerRequestDto);

    AuthResponseDto login(AuthRequestDto authRequestDto);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

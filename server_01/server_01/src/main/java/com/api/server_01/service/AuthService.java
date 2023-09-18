package com.api.server_01.service;

import com.api.server_01.entity.UserEntity;
import com.api.server_01.entity.dto.AuthRequestDto;
import com.api.server_01.entity.dto.AuthResponseDto;
import com.api.server_01.entity.dto.RegisterRequestDto;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto registerRequestDto, String siteUrl) throws MessagingException, UnsupportedEncodingException;

    AuthResponseDto login(AuthRequestDto authRequestDto);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    boolean verify(String verificationCode);
}

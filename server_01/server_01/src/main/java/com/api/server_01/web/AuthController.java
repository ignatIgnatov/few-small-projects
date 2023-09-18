package com.api.server_01.web;

import com.api.server_01.entity.dto.AuthRequestDto;
import com.api.server_01.entity.dto.AuthResponseDto;
import com.api.server_01.entity.dto.RegisterRequestDto;
import com.api.server_01.service.impl.AuthServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
            @RequestBody RegisterRequestDto registerRequestDto, HttpServletRequest request
    ) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(authServiceImpl.register(registerRequestDto, getSiteURL(request)));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody AuthRequestDto authRequestDto
    ) {
        if (authServiceImpl.login(authRequestDto) != null) {
            return ResponseEntity.ok(authServiceImpl.login(authRequestDto));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authServiceImpl.refreshToken(request, response);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}

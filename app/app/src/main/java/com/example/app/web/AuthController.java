package com.example.app.web;

import com.example.app.model.dto.UserLoginDto;
import com.example.app.model.dto.UserRegisterDto;
import com.example.app.model.response.MessageResponse;
import com.example.app.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto userLoginDto) {

        return userService.loginUser(userLoginDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = userService.cleanCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been logged out!"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto userRegisterDto, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        if (userService.existsByUsername(userRegisterDto)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: This username already exists!"));
        }

        if (userService.existsByEmail(userRegisterDto)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: This email already exists!"));
        }

        userService.registerUser(userRegisterDto, getSiteURL(request));

        return ResponseEntity.ok(new MessageResponse("Registration is successful!"));
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "redirect:login";
        } else {
            return "verify_fail";
        }
    }

}

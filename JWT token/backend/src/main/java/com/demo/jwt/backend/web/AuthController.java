package com.demo.jwt.backend.web;

import com.demo.jwt.backend.config.UserAuthProvider;
import com.demo.jwt.backend.model.dto.CredentialsDto;
import com.demo.jwt.backend.model.dto.SignUpDto;
import com.demo.jwt.backend.model.dto.UserDto;
import com.demo.jwt.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider authProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
       UserDto userDto = userService.login(credentialsDto);
       userDto.setToken(authProvider.createToken(userDto.getLogin()));

       return ResponseEntity.ok(userDto);
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.register(signUpDto);
        userDto.setToken(authProvider.createToken(userDto.getLogin()));

        return ResponseEntity.created(URI.create("/users/" + userDto.getId())).body(userDto);
    }
}

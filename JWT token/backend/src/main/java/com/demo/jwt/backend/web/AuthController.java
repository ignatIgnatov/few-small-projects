package com.demo.jwt.backend.web;

import com.demo.jwt.backend.config.UserAuthProvider;
import com.demo.jwt.backend.mapper.UserMapper;
import com.demo.jwt.backend.model.dto.CredentialsDto;
import com.demo.jwt.backend.model.dto.SignUpDto;
import com.demo.jwt.backend.model.dto.UserDto;
import com.demo.jwt.backend.model.entity.Token;
import com.demo.jwt.backend.model.entity.TokenType;
import com.demo.jwt.backend.model.entity.UserEntity;
import com.demo.jwt.backend.repository.TokenRepository;
import com.demo.jwt.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider authProvider;
    private final TokenRepository tokenRepository;
    private final UserMapper userMapper;

    public AuthController(UserService userService, UserAuthProvider authProvider, TokenRepository tokenRepository, UserMapper userMapper) {
        this.userService = userService;
        this.authProvider = authProvider;
        this.tokenRepository = tokenRepository;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        String jwtToken = authProvider.createToken(userDto.getEmail());
        userDto.setToken(jwtToken);
        revokedAllUserTokens(userDto);
        saveUserToken(userDto, jwtToken);

        return ResponseEntity.ok(userDto);
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.register(signUpDto);

        String jwtToken = authProvider.createToken(signUpDto.getEmail());
        userDto.setToken(jwtToken);

        saveUserToken(userDto, jwtToken);

        return ResponseEntity.created(URI.create("/users/" + userDto.getId())).body(userDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout(String token) {

        Token jwt = tokenRepository.findByToken(token).orElse(null);

        if (jwt != null) {
            jwt.setRevoked(true);
            jwt.setExpired(true);
        }

        return ResponseEntity.ok().build();
    }

    private void revokedAllUserTokens(UserDto userDto) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(userDto.getId().intValue());

        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
        }

        tokenRepository.saveAll(validUserTokens);

    }

    private void saveUserToken(UserDto userDto, String jwtToken) {
        Token token = new Token(
                jwtToken,
                TokenType.BEARER,
                false,
                false,
                userMapper.toUserEntity(userDto)
        );

        tokenRepository.save(token);
    }


}

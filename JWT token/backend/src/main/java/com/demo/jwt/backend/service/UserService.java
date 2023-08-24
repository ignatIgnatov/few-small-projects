package com.demo.jwt.backend.service;

import com.demo.jwt.backend.model.dto.CredentialsDto;
import com.demo.jwt.backend.model.dto.SignUpDto;
import com.demo.jwt.backend.model.dto.UserDto;

public interface UserService {

    UserDto findByEmail(String email);

    UserDto register(SignUpDto signUpDto);

    UserDto login(CredentialsDto credentialsDto);
}

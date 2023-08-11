package com.demo.jwt.backend.service;

import com.demo.jwt.backend.model.dto.CredentialsDto;
import com.demo.jwt.backend.model.dto.SignUpDto;
import com.demo.jwt.backend.model.dto.UserDto;

public interface UserService {

    UserDto findByLogin(String login);

    UserDto register(SignUpDto userDto);

    UserDto login(CredentialsDto credentialsDto);
}

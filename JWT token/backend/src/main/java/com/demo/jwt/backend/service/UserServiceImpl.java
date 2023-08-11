package com.demo.jwt.backend.service;

import com.demo.jwt.backend.exception.AppException;
import com.demo.jwt.backend.mapper.UserMapper;
import com.demo.jwt.backend.model.dto.CredentialsDto;
import com.demo.jwt.backend.model.dto.SignUpDto;
import com.demo.jwt.backend.model.dto.UserDto;
import com.demo.jwt.backend.model.entity.UserEntity;
import com.demo.jwt.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto findByLogin(String login) {
       UserEntity user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

       return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto) {

       UserEntity userEntity = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

       if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), userEntity.getPassword())) {
           return userMapper.toUserDto(userEntity);
       }

       throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }


    public UserDto register(SignUpDto userDto) {

        Optional<UserEntity> userOpt = userRepository.findByLogin(userDto.getLogin());

        if (userOpt.isPresent()) {
            throw new AppException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = userMapper.signUpToUser(userDto);

        userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        UserEntity savedUser = userRepository.save(userEntity);

        return userMapper.toUserDto(savedUser);
    }
}

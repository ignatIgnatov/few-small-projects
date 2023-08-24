package com.demo.jwt.backend.service;

import com.demo.jwt.backend.exception.AppException;
import com.demo.jwt.backend.mapper.UserMapper;
import com.demo.jwt.backend.model.dto.CredentialsDto;
import com.demo.jwt.backend.model.dto.SignUpDto;
import com.demo.jwt.backend.model.dto.UserDto;
import com.demo.jwt.backend.model.entity.UserEntity;
import com.demo.jwt.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto findByEmail(String email) {
       UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

       return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto) {

       UserEntity userEntity = userRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

       if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), userEntity.getPassword())) {
           return userMapper.toUserDto(userEntity);
       }

       throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }


    public UserDto register(SignUpDto signUpDto) {

        Optional<UserEntity> userOpt = userRepository.findByEmail(signUpDto.getEmail());

        if (userOpt.isPresent()) {
            throw new AppException("This username already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = userMapper.signUpToUser(signUpDto);

        userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));

        UserEntity savedUser = userRepository.save(userEntity);

        return userMapper.toUserDto(savedUser);
    }
}

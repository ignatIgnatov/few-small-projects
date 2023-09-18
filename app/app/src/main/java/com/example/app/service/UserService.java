package com.example.app.service;

import com.example.app.model.dto.UserLoginDto;
import com.example.app.model.dto.UserRegisterDto;
import com.example.app.model.view.UserPasswordViewModel;
import com.example.app.model.view.UserViewModel;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto, String siteUrl) throws MessagingException, UnsupportedEncodingException;

    ResponseEntity<?> loginUser(UserLoginDto userLoginDto);

    boolean existsByUsername(UserRegisterDto userRegisterDto);

    boolean existsByEmail(UserRegisterDto userRegisterDto);

    ResponseCookie cleanCookie();

    Optional<UserViewModel> getUserById(Long id);

    List<UserViewModel> getAllUsers();

    Long updateUser(UserViewModel userViewModel);

    void deleteUser(Long id);

    Long updatePassword(UserPasswordViewModel userPasswordViewModel);

    public boolean verify(String verificationCode);

}

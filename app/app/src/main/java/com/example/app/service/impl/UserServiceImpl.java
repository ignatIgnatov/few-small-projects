package com.example.app.service.impl;

import com.example.app.model.dto.UserLoginDto;
import com.example.app.model.dto.UserRegisterDto;
import com.example.app.model.entity.RoleEntity;
import com.example.app.model.entity.UserEntity;
import com.example.app.model.response.UserInfoResponse;
import com.example.app.model.view.UserPasswordViewModel;
import com.example.app.model.view.UserViewModel;
import com.example.app.repository.RoleRepository;
import com.example.app.repository.UserRepository;
import com.example.app.config.JwtUtils;
import com.example.app.model.service.UserDetailsImpl;
import com.example.app.service.RoleService;
import com.example.app.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final JavaMailSender mailSender;

    public UserServiceImpl(ModelMapper modelMapper, AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository, RoleService roleService, PasswordEncoder passwordEncoder,
                           JwtUtils jwtUtils, JavaMailSender mailSender) {
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.mailSender = mailSender;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto, String siteUrl) throws MessagingException, UnsupportedEncodingException {
        UserEntity user = modelMapper.map(userRegisterDto, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        roleService.initRoles();

        Set<RoleEntity> roles = roleService.addRoles(userRegisterDto);

        user.setRoles(roles);

        String code = new Random(64).toString();
        user.setVerificationCode(code);
        user.setEnabled(false);

        userRepository.save(user);
        sendVerificationEmail(user, siteUrl);
    }

    private void sendVerificationEmail(UserEntity user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "mail@gmail.com";
        String senderName = "I. Ignatov LTD";
        String subject = "Please verify your registration";
        String content = "Hello [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\">verify</a></h3>"
                + "Thank you,<br>"
                + "<br>"
                +"----------------<br>"
                + "I. Ignatov LTD.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }


    @Override
    public ResponseEntity<?> loginUser(UserLoginDto userLoginDto) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(
                        userDetails.getId(),
                        userDetails.getFullName(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        authorities
                ));
    }

    @Override
    public boolean existsByUsername(UserRegisterDto userRegisterDto) {
        return userRepository.existsByUsername(userRegisterDto.getUsername());
    }

    @Override
    public boolean existsByEmail(UserRegisterDto userRegisterDto) {
        return userRepository.existsByEmail(userRegisterDto.getEmail());
    }

    @Override
    public ResponseCookie cleanCookie() {
        return jwtUtils.getCleanJwtCookie();
    }

    @Override
    public Optional<UserViewModel> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserViewModel.class));
    }

    @Override
    public List<UserViewModel> getAllUsers() {

        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long updateUser(UserViewModel userViewModel) {

        UserEntity userEntity = userRepository.findById(userViewModel.getId()).orElse(null);

        if (userEntity == null) {
            return null;
        }

        userEntity
                .setUsername(userViewModel.getUsername())
                .setEmail(userViewModel.getEmail())
                .setRoles(userViewModel.getRoles());

        UserRegisterDto tempUser = modelMapper.map(userEntity, UserRegisterDto.class);

        if (!existsByUsername(tempUser) && !existsByEmail(tempUser)) {
            userRepository.save(userEntity);
        } else {
            throw new UnsupportedOperationException("Error: Username or password exists!");
        }

        return userEntity.getId();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Long updatePassword(UserPasswordViewModel userPasswordViewModel) {

        UserEntity userEntity = userRepository.findById(userPasswordViewModel.getId()).orElse(null);

        if (userEntity == null) {
            return null;
        }

        if (userPasswordViewModel.getPassword().matches(userPasswordViewModel.getConfirmPassword())) {
            String pwd = userPasswordViewModel.getPassword();
            userEntity.setPassword(passwordEncoder.encode(userPasswordViewModel.getPassword()));
        } else {
            throw new UnsupportedOperationException("Error: Wrong password!");
        }

        return userRepository.save(userEntity).getId();
    }

    public boolean verify(String verificationCode) {
        UserEntity user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }
    }
}

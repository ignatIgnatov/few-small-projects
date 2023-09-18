package com.example.app.model.service;

import com.example.app.model.entity.RoleEntity;
import com.example.app.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long SERIAL_VERSION_UID = 1L;
    private final UserEntity user;

    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserEntity getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public UserDetailsImpl setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return user.getFullName();
    }

    public UserDetailsImpl setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return user.getEmail();
    }

    public UserDetailsImpl setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDetailsImpl(UserEntity user) {
        this.user = user;
    }

    public UserDetailsImpl(UserEntity user, Long id,
                           String fullName,
                           String username,
                           String email,
                           String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }



    public static UserDetailsImpl build(UserEntity user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user,
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public UserDetailsImpl setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDetailsImpl setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDetailsImpl setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}

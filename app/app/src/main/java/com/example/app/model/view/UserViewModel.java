package com.example.app.model.view;

import com.example.app.model.entity.RoleEntity;

import java.util.Set;

public class UserViewModel {

    private Long id;
    private String fullName;
    private String username;
    private String email;
    private Set<RoleEntity> roles;

    public UserViewModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public UserViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserViewModel setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}

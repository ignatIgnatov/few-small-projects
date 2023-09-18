package com.example.app.model.response;

import java.util.List;

public class UserInfoResponse {

    private Long id;
    private String fullName;
    private String username;
    private String email;
    private List<String> roles;

    public UserInfoResponse(Long id, String fullName, String username, String email, List<String> roles) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public UserInfoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserInfoResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserInfoResponse setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfoResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserInfoResponse setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}

package com.demo.jwt.backend.model.dto;

public class CredentialsDto {

    private String email;
    private char[] password;

    public CredentialsDto() {
    }

    public String getEmail() {
        return email;
    }

    public CredentialsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public char[] getPassword() {
        return password;
    }

    public CredentialsDto setPassword(char[] password) {
        this.password = password;
        return this;
    }
}

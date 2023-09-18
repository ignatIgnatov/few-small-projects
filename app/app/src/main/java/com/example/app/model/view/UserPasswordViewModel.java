package com.example.app.model.view;

public class UserPasswordViewModel {

    Long id;
    String password;
    String confirmPassword;

    public UserPasswordViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserPasswordViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserPasswordViewModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserPasswordViewModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}

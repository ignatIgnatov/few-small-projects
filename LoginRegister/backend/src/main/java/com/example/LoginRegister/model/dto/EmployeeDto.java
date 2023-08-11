package com.example.LoginRegister.model.dto;

public class EmployeeDto {

    private Long id;
    private String name;
    private String email;
    private String password;

    public EmployeeDto(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public EmployeeDto() {
    }

    public Long getId() {
        return id;
    }

    public EmployeeDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmployeeDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public EmployeeDto setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

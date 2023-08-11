package com.example.LoginRegister.service;

import com.example.LoginRegister.model.dto.EmployeeDto;
import com.example.LoginRegister.model.dto.LoginDto;
import com.example.LoginRegister.payload.response.LoginResponse;

public interface EmployeeService {

    String addEmployee(EmployeeDto employeeDto);

    LoginResponse loginEmployee(LoginDto loginDto);
}

package com.example.LoginRegister.service.impl;

import com.example.LoginRegister.model.dto.EmployeeDto;
import com.example.LoginRegister.model.dto.LoginDto;
import com.example.LoginRegister.model.entity.Employee;
import com.example.LoginRegister.payload.response.LoginResponse;
import com.example.LoginRegister.repository.EmployeeRepository;
import com.example.LoginRegister.service.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getEmail(),
                this.passwordEncoder.encode(employeeDto.getPassword())
        );

        employeeRepository.save(employee);
        return employee.getName();
    }

    @Override
    public LoginResponse loginEmployee(LoginDto loginDto) {

        Employee employee = employeeRepository.findByEmail(loginDto.getEmail());

        if (employee != null) {
            String password = loginDto.getPassword();
            String encodedPassword = employee.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            System.out.println(loginDto.getEmail());
            System.out.println(loginDto.getPassword());
            if (isPwdRight) {
                Optional<Employee> employee1 = employeeRepository.findByEmailAndPassword(
                        loginDto.getEmail(), encodedPassword);

                if (employee1.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Wrong Password", false);
            }
        } else {
            return new LoginResponse("Wrong Email", false);
        }
    }
}

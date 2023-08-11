package com.example.LoginRegister.web;

import com.example.LoginRegister.model.dto.EmployeeDto;
import com.example.LoginRegister.model.dto.LoginDto;
import com.example.LoginRegister.payload.response.LoginResponse;
import com.example.LoginRegister.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public String saveEmployee(@RequestBody EmployeeDto employeeDto) {
        String id = employeeService.addEmployee(employeeDto);
        return id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDto loginDto) {

        LoginResponse loginResponse = employeeService.loginEmployee(loginDto);
        return ResponseEntity.ok(loginResponse);
    }
}

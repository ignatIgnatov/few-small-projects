package com.api.server_01.entity.dto;

import com.api.server_01.entity.enums.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private RoleEntity role;
}

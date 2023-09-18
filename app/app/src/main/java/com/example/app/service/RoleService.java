package com.example.app.service;

import com.example.app.model.dto.UserRegisterDto;
import com.example.app.model.entity.RoleEntity;
import com.example.app.model.entity.enums.ERole;

import java.util.Set;

public interface RoleService {

    void initRoles();

    Set<RoleEntity> addRoles(UserRegisterDto userRegisterDto);

    RoleEntity findByName(ERole name);
}

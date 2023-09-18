package com.example.app.service.impl;

import com.example.app.model.dto.UserRegisterDto;
import com.example.app.model.entity.RoleEntity;
import com.example.app.model.entity.enums.ERole;
import com.example.app.repository.RoleRepository;
import com.example.app.repository.UserRepository;
import com.example.app.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void initRoles() {
        if (roleRepository.count() == 0) {
            RoleEntity roleUser = new RoleEntity().setName(ERole.ROLE_USER);
            RoleEntity roleAdmin = new RoleEntity().setName(ERole.ROLE_ADMIN);
            RoleEntity roleCompany = new RoleEntity().setName(ERole.ROLE_MODERATOR);

            roleRepository.save(roleUser);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleCompany);
        }
    }

    @Override
    public Set<RoleEntity> addRoles(UserRegisterDto userRegisterDto) {

        Set<String> strRoles = userRegisterDto.getRole();
        Set<RoleEntity> roles = new HashSet<>();

        if (userRepository.count() == 0) {
            RoleEntity role = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(role);

        } else if (strRoles == null || strRoles.isEmpty()) {
            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);

        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleEntity adminRole = findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    case "company":
                        RoleEntity companyRole = findByName(ERole.ROLE_MODERATOR);
                        roles.add(companyRole);
                        break;
                    default:
                        RoleEntity userRole = findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }
        return roles;
    }

    @Override
    public RoleEntity findByName(ERole name) {

        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    }
}

package com.example.app.model.entity;

import com.example.app.model.entity.enums.ERole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ERole name;

    public RoleEntity() {
    }

    public ERole getName() {
        return name;
    }

    public RoleEntity setName(ERole name) {
        this.name = name;
        return this;
    }
}

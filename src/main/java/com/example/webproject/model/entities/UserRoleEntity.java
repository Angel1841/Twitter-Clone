package com.example.webproject.model.entities;


import com.example.webproject.model.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;


    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

}

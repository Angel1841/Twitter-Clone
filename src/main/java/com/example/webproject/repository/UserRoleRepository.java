package com.example.webproject.repository;

import com.example.webproject.model.entities.UserRoleEntity;
import com.example.webproject.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum role);

}

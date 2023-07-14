package com.example.springsecuritystudy.repository;

import com.example.springsecuritystudy.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
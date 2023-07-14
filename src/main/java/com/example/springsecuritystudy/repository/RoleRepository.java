package com.example.springsecuritystudy.repository;

import com.example.springsecuritystudy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}

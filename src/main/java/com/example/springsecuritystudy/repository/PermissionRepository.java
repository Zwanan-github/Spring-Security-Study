package com.example.springsecuritystudy.repository;

import com.example.springsecuritystudy.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
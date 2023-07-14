package com.example.springsecuritystudy.repository;

import com.example.springsecuritystudy.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {
}
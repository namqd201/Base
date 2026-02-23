package com.tmdt.base.domain.repository;

import com.tmdt.base.domain.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RolePermissionRepository{
    RolePermission save(RolePermission rolePermission);

    void deleteAllByRoleId( Long roleId);

    void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);

    List<RolePermission> findAllByRoleId(Long roleId);

    Optional<RolePermission> findByRoleIdAndPermissionId(Long roleId, Long permissionId);
}

package com.tmdt.base.infrastructure.persistence.adapter;

import com.tmdt.base.domain.model.RolePermission;
import com.tmdt.base.domain.repository.RolePermissionRepository;
import com.tmdt.base.infrastructure.persistence.jpa.JpaRolePermissionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RolePermissionRepositoryImpl implements RolePermissionRepository {

    private final JpaRolePermissionRepository jpa;

    public RolePermissionRepositoryImpl(JpaRolePermissionRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public RolePermission save(RolePermission rolePermission) {
        return jpa.save(rolePermission);
    }

    @Override
    public void deleteAllByRoleId(Long roleId) {
        jpa.deleteAllByRoleId(roleId);
    }

    @Override
    public void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        jpa.deleteByRoleIdAndPermissionId(roleId, permissionId);
    }

    @Override
    public List<RolePermission> findAllByRoleId(Long roleId) {
        return jpa.findAllByRoleId(roleId);
    }

    @Override
    public Optional<RolePermission> findByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        return jpa.findByRoleIdAndPermissionId(roleId, permissionId);
    }
}

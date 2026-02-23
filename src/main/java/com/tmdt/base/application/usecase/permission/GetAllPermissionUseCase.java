package com.tmdt.base.application.usecase.permission;

import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllPermissionUseCase {
    private final PermissionRepository permissionRepository;

    public GetAllPermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> findAll() {
        return permissionRepository.findAllByIsDeletedFalse();
    }
}

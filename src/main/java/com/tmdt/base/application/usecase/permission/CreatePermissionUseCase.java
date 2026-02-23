package com.tmdt.base.application.usecase.permission;

import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.shared.config.exception.PermissionNameAlreadyExistsException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreatePermissionUseCase {
    private final PermissionRepository permissionRepository;

    public CreatePermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission execute(String permissionName) {
        if (permissionRepository.existsByPermissionNameAndIsDeletedFalse(permissionName)) {
            throw new PermissionNameAlreadyExistsException(permissionName);
        }

        Permission permission = new Permission();
        permission.setPermissionName(permissionName);
        permission.setCreatedAt(LocalDateTime.now());
//        permission.setCreatedBy();
        permission.setIsActive(true);
        permission.setIsDeleted(false);

        return permissionRepository.save(permission);
    }
}

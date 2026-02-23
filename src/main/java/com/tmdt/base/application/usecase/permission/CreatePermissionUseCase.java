package com.tmdt.base.application.usecase.permission;

import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.shared.components.CurrentSession;
import com.tmdt.base.shared.config.exception.PermissionNameAlreadyExistsException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreatePermissionUseCase {
    private final PermissionRepository permissionRepository;
    private final CurrentSession currentSession;
    public CreatePermissionUseCase(PermissionRepository permissionRepository, CurrentSession currentSession) {
        this.permissionRepository = permissionRepository;
        this.currentSession = currentSession;
    }

    public Permission create(String permissionName) {
        if (permissionRepository.existsByPermissionNameAndIsDeletedFalse(permissionName)) {
            throw new PermissionNameAlreadyExistsException(permissionName);
        }


        Long id = currentSession.getCurrentUserId();

        Permission permission = new Permission();
        permission.setPermissionName(permissionName);
        permission.setCreatedAt(LocalDateTime.now());
        permission.setCreatedBy(id);
        permission.setIsActive(true);
        permission.setIsDeleted(false);

        return permissionRepository.save(permission);
    }
}

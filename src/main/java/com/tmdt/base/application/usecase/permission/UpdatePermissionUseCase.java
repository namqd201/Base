package com.tmdt.base.application.usecase.permission;

import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.shared.components.CurrentSession;
import com.tmdt.base.shared.config.exception.PermissionNameAlreadyExistsException;
import com.tmdt.base.shared.config.exception.PermissionNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdatePermissionUseCase {

    private final PermissionRepository permissionRepository;
    private final CurrentSession currentSession;

    public UpdatePermissionUseCase(PermissionRepository permissionRepository, CurrentSession currentSession) {
        this.permissionRepository = permissionRepository;
        this.currentSession = currentSession;
    }

    public Permission updatePermission(Long id, String permissionName) {
        Permission permission = permissionRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));

        if (permissionRepository.existsByPermissionNameAndIdNotAndIsDeletedFalse(permissionName, id)) {
            throw new PermissionNameAlreadyExistsException(permissionName);
        }

        Long userId = currentSession.getCurrentUserId();
        
        permission.setPermissionName(permissionName);
        permission.setUpdatedAt(LocalDateTime.now());
        permission.setUpdatedBy(userId);
        permission.setIsActive(true);
        permission.setIsDeleted(false);

        return permissionRepository.save(permission);
    }
}

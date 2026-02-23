package com.tmdt.base.application.usecase.permission;

import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.shared.components.CurrentSession;
import com.tmdt.base.shared.config.exception.PermissionNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeletePermissionUseCase {

    private final PermissionRepository permissionRepository;
    private final CurrentSession currentSession;

    public DeletePermissionUseCase(PermissionRepository permissionRepository, CurrentSession currentSession) {
        this.permissionRepository = permissionRepository;
        this.currentSession = currentSession;
    }

    public void delete(Long id) {
        Permission permission = permissionRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));

        Long userId = currentSession.getCurrentUserId();

        permission.setIsDeleted(true);
        permission.setDeletedAt(LocalDateTime.now());
        permission.setDeletedBy(userId);

        permissionRepository.save(permission);
    }
}

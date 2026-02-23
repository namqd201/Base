package com.tmdt.base.application.usecase.permission;

import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.shared.config.exception.PermissionNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeletePermissionUseCase {

    private final PermissionRepository permissionRepository;

    public DeletePermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public void execute(Long id, Long deletedBy) {
        Permission permission = permissionRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));

        permission.setIsDeleted(true);
        permission.setDeletedAt(LocalDateTime.now());
        permission.setDeletedBy(deletedBy);

        permissionRepository.save(permission);
    }
}

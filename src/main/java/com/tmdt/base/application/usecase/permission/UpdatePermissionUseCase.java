package com.tmdt.base.application.usecase.permission;

import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.shared.config.exception.PermissionNameAlreadyExistsException;
import com.tmdt.base.shared.config.exception.PermissionNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UpdatePermissionUseCase {
    private final PermissionRepository permissionRepository;

    public UpdatePermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission execute(Long id, String permissionName) {
        Permission permission = permissionRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));

        if (permissionRepository.existsByPermissionNameAndIdNotAndIsDeletedFalse(permissionName, id)) {
            throw new PermissionNameAlreadyExistsException(permissionName);
        }

        permission.setPermissionName(permissionName);

        return permissionRepository.save(permission);
    }
}

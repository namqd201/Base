package com.tmdt.base.application.usecase.permission;

import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.shared.config.exception.PermissionNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class GetPermissionByIdUseCase {
    private final PermissionRepository permissionRepository;

    public GetPermissionByIdUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission execute(Long id) {
        return permissionRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));
    }
}

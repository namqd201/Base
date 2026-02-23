package com.tmdt.base.application.usecase.permission;

import com.tmdt.base.application.dto.PermissionDto;
import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.presentation.mapper.PermissionMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllPermissionUseCase {
    private final PermissionRepository permissionRepository;

    public GetAllPermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<PermissionDto> findAll() {
        List<Permission> permissions = permissionRepository.findAllByIsDeletedFalse();
        permissions = permissions.stream().toList();
        return permissions.stream().map(PermissionMapper.INSTANCE::entityToDto).toList();
    }
}

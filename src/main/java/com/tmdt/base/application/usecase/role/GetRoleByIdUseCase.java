package com.tmdt.base.application.usecase.role;

import com.tmdt.base.application.dto.RoleDto;
import com.tmdt.base.application.dto.PermissionDto;
import com.tmdt.base.domain.model.Role;
import com.tmdt.base.domain.model.RolePermission;
import com.tmdt.base.domain.repository.RoleRepository;
import com.tmdt.base.domain.repository.RolePermissionRepository;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.presentation.mapper.RoleMapper;
import com.tmdt.base.presentation.mapper.PermissionMapper;
import com.tmdt.base.shared.config.exception.RoleNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRoleByIdUseCase {

    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionRepository permissionRepository;

    public GetRoleByIdUseCase(RoleRepository roleRepository, RolePermissionRepository rolePermissionRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionRepository = permissionRepository;
    }

    public RoleDto execute(Long id) {
        Role role = roleRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RoleNotFoundException(id));

        RoleDto dto = RoleMapper.INSTANCE.entityToDto(role);
        List<RolePermission> rps = rolePermissionRepository.findAllByRoleId(role.getId());
        List<PermissionDto> perms = rps.stream()
                .map(rp -> permissionRepository.findByIdAndIsDeletedFalse(rp.getPermissionId()))
                .filter(java.util.Optional::isPresent)
                .map(opt -> PermissionMapper.INSTANCE.entityToDto(opt.get()))
                .toList();
        dto.setPermissions(perms);
        return dto;
    }
}


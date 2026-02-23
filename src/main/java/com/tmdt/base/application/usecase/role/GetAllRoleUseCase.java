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
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllRoleUseCase {

    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionRepository permissionRepository;

    public GetAllRoleUseCase(RoleRepository roleRepository, RolePermissionRepository rolePermissionRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAllByIsDeletedFalse();
        return roles.stream().map(role -> {
            RoleDto dto = RoleMapper.INSTANCE.entityToDto(role);
            List<RolePermission> rps = rolePermissionRepository.findAllByRoleId(role.getId());
            List<PermissionDto> perms = rps.stream()
                    .map(rp -> permissionRepository.findByIdAndIsDeletedFalse(rp.getPermissionId()))
                    .filter(java.util.Optional::isPresent)
                    .map(opt -> PermissionMapper.INSTANCE.entityToDto(opt.get()))
                    .toList();
            dto.setPermissions(perms);
            return dto;
        }).toList();
    }
}


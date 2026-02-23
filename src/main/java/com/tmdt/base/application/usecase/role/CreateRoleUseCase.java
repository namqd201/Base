package com.tmdt.base.application.usecase.role;

import com.tmdt.base.domain.model.Role;
import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.model.RolePermission;
import com.tmdt.base.domain.repository.RoleRepository;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.domain.repository.RolePermissionRepository;
import com.tmdt.base.shared.config.exception.PermissionNotFoundException;
import com.tmdt.base.shared.components.CurrentSession;
import com.tmdt.base.shared.config.exception.RoleNameAlreadyExistsException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateRoleUseCase {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final CurrentSession currentSession;

    public CreateRoleUseCase(RoleRepository roleRepository, PermissionRepository permissionRepository, RolePermissionRepository rolePermissionRepository, CurrentSession currentSession) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.currentSession = currentSession;
    }

    public Role create(String roleName, java.util.List<Long> permissionIds) {
        if (roleRepository.existsByRoleNameAndIsDeletedFalse(roleName)) {
            throw new RoleNameAlreadyExistsException(roleName);
        }

        Long userId = currentSession.getCurrentUserId();

        Role role = new Role();
        role.setRoleName(roleName);
        role.setCreatedAt(LocalDateTime.now());
        role.setCreatedBy(userId);
        role.setIsActive(true);
        role.setIsDeleted(false);

        Role saved = roleRepository.save(role);

        if (permissionIds != null && !permissionIds.isEmpty()) {
            for (Long pid : permissionIds) {
                Permission permission = permissionRepository.findByIdAndIsDeletedFalse(pid)
                        .orElseThrow(() -> new PermissionNotFoundException(pid));
                RolePermission rp = new RolePermission();
                rp.setRoleId(saved.getId());
                rp.setPermissionId(permission.getId());
                rolePermissionRepository.save(rp);
            }
        }

        return saved;
    }
}


package com.tmdt.base.application.usecase.role;

import com.tmdt.base.domain.model.Role;
import com.tmdt.base.domain.repository.RoleRepository;
import com.tmdt.base.domain.repository.RolePermissionRepository;
import com.tmdt.base.shared.components.CurrentSession;
import com.tmdt.base.shared.config.exception.RoleNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class DeleteRoleUseCase {

    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final CurrentSession currentSession;

    public DeleteRoleUseCase(RoleRepository roleRepository, RolePermissionRepository rolePermissionRepository, CurrentSession currentSession) {
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.currentSession = currentSession;
    }

    @Transactional
    public void execute(Long id) {
        Role role = roleRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RoleNotFoundException(id));

        Long userId = currentSession.getCurrentUserId();

        // remove associations in join table
        rolePermissionRepository.deleteAllByRoleId(id);
        role.setIsDeleted(true);
        role.setDeletedAt(LocalDateTime.now());
        role.setDeletedBy(userId);

        roleRepository.save(role);
    }
}


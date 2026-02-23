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
import com.tmdt.base.shared.config.exception.RoleNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UpdateRoleUseCase {

    @PersistenceContext
    private EntityManager entityManager;

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final CurrentSession currentSession;

    public UpdateRoleUseCase(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            RolePermissionRepository rolePermissionRepository,
            CurrentSession currentSession
    ) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.currentSession = currentSession;
    }

    @Transactional
    public Role update(Long id, String roleName, List<Long> permissionIds) {

        Role role = roleRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RoleNotFoundException(id));

        if (roleRepository.existsByRoleNameAndIdNotAndIsDeletedFalse(roleName, id)) {
            throw new RoleNameAlreadyExistsException(roleName);
        }

        Long userId = currentSession.getCurrentUserId();

        role.setRoleName(roleName);
        role.setUpdatedAt(LocalDateTime.now());
        role.setUpdatedBy(userId);
        role.setIsActive(true);
        role.setIsDeleted(false);

        Role saved = roleRepository.save(role);

        // delete old
        rolePermissionRepository.deleteAllByRoleId(saved.getId());

        // FORCE sync với DB
        entityManager.flush();
        entityManager.clear();

        if (permissionIds != null) {

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


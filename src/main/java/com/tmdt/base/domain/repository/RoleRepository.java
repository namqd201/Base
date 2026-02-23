package com.tmdt.base.domain.repository;

import com.tmdt.base.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Role save(Role role);

    Optional<Role> findByIdAndIsDeletedFalse(Long id);

    List<Role> findAllByIsDeletedFalse();

    boolean existsByIdAndIsDeletedFalse(Long id);

    Optional<Role> findByRoleName(String roleName);

    boolean existsByRoleNameAndIsDeletedFalse(String roleName);

    boolean existsByRoleNameAndIdNotAndIsDeletedFalse(String roleName, Long id);
}


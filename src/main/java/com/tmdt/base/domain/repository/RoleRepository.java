package com.tmdt.base.domain.repository;

import com.tmdt.base.domain.model.Role;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findByRoleName(String roleName);

    boolean existsByRoleName(String roleName);

    Role save(Role role);
}


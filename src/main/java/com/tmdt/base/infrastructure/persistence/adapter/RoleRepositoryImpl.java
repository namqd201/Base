package com.tmdt.base.infrastructure.persistence.adapter;

import com.tmdt.base.domain.model.Role;
import com.tmdt.base.domain.repository.RoleRepository;
import com.tmdt.base.infrastructure.persistence.jpa.JpaRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryImpl(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return jpaRoleRepository.findByRoleName(roleName);
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return jpaRoleRepository.existsByRoleName(roleName);
    }

    @Override
    public Role save(Role role) {
        return jpaRoleRepository.save(role);
    }
}


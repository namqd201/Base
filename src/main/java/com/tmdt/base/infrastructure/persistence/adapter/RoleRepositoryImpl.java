package com.tmdt.base.infrastructure.persistence.adapter;

import com.tmdt.base.domain.model.Role;
import com.tmdt.base.domain.repository.RoleRepository;
import com.tmdt.base.infrastructure.persistence.jpa.JpaRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryImpl(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Role save(Role role) {
        return jpaRoleRepository.save(role);
    }

    @Override
    public Optional<Role> findByIdAndIsDeletedFalse(Long id) {
        return jpaRoleRepository.findByIdAndIsDeletedFalse(id);
    }

    @Override
    public List<Role> findAllByIsDeletedFalse() {
        return jpaRoleRepository.findAllByIsDeletedFalse();
    }

    @Override
    public boolean existsByIdAndIsDeletedFalse(Long id) {
        return jpaRoleRepository.existsByIdAndIsDeletedFalse(id);
    }

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return jpaRoleRepository.findByRoleName(roleName);
    }

    @Override
    public boolean existsByRoleNameAndIsDeletedFalse(String roleName) {
        return jpaRoleRepository.existsByRoleNameAndIsDeletedFalse(roleName);
    }

    @Override
    public boolean existsByRoleNameAndIdNotAndIsDeletedFalse(String roleName, Long id) {
        return jpaRoleRepository.existsByRoleNameAndIdNotAndIsDeletedFalse(roleName, id);
    }
}


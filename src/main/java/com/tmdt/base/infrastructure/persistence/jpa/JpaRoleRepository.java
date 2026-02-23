package com.tmdt.base.infrastructure.persistence.jpa;

import com.tmdt.base.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByIdAndIsDeletedFalse(Long id);

    List<Role> findAllByIsDeletedFalse();

    boolean existsByIdAndIsDeletedFalse(Long id);

    Optional<Role> findByRoleName(String roleName);

    boolean existsByRoleNameAndIsDeletedFalse(String roleName);

    boolean existsByRoleNameAndIdNotAndIsDeletedFalse(String roleName, Long id);
}


package com.tmdt.base.infrastructure.persistence.jpa;

import com.tmdt.base.domain.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface JpaPermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {

    Optional<Permission> findByIdAndIsDeletedFalse(Long id);

    List<Permission> findAllByIsDeletedFalse();

    boolean existsByIdAndIsDeletedFalse(Long id);

    boolean existsByPermissionNameAndIsDeletedFalse(String permissionName);

    boolean existsByPermissionNameAndIdNotAndIsDeletedFalse(String permissionName, Long id);
}

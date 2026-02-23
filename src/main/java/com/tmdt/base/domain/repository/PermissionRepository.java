package com.tmdt.base.domain.repository;

import com.tmdt.base.domain.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository {
    Permission save(Permission permission);

    Optional<Permission> findByIdAndIsDeletedFalse(Long id);

    List<Permission> findAllByIsDeletedFalse();

    boolean existsByIdAndIsDeletedFalse(Long id);

    boolean existsByPermissionNameAndIsDeletedFalse(String permissionName);

    boolean existsByPermissionNameAndIdNotAndIsDeletedFalse(String permissionName, Long id);
}

package com.tmdt.base.infrastructure.persistence.adapter;

import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.domain.repository.PermissionRepository;
import com.tmdt.base.infrastructure.persistence.jpa.JpaPermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    private final JpaPermissionRepository jpaRepository;

    public PermissionRepositoryImpl(JpaPermissionRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Permission save(Permission permission) {
        return jpaRepository.save(permission);
    }

    @Override
    public Optional<Permission> findByIdAndIsDeletedFalse(Long id) {
        return jpaRepository.findByIdAndIsDeletedFalse(id);
    }

    @Override
    public List<Permission> findAllByIsDeletedFalse() {
        return jpaRepository.findAllByIsDeletedFalse();
    }

    @Override
    public boolean existsByIdAndIsDeletedFalse(Long id) {
        return jpaRepository.existsByIdAndIsDeletedFalse(id);
    }

    @Override
    public boolean existsByPermissionNameAndIsDeletedFalse(String permissionName) {
        return jpaRepository.existsByPermissionNameAndIsDeletedFalse(permissionName);
    }

    @Override
    public boolean existsByPermissionNameAndIdNotAndIsDeletedFalse(String permissionName, Long id) {
        return jpaRepository.existsByPermissionNameAndIdNotAndIsDeletedFalse(permissionName, id);
    }

}

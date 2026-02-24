package com.tmdt.base.infrastructure.persistence.jpa;

import com.tmdt.base.domain.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserAccount, Long>, JpaSpecificationExecutor<UserAccount> {
    
    @Query("SELECT DISTINCT u FROM UserAccount u " +
            "LEFT JOIN FETCH u.userRoles ur " +
            "LEFT JOIN FETCH ur.role r " +
            "LEFT JOIN FETCH r.rolePermissions rp " +
            "LEFT JOIN FETCH rp.permission " +
            "WHERE u.id = :id AND u.isActive = true")
    Optional<UserAccount> findByIdAndIsActiveTrueWithRoles(@Param("id") Long id);
}

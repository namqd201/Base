package com.tmdt.base.infrastructure.persistence.jpa;

import com.tmdt.base.domain.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaUserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);

    @Query("SELECT DISTINCT u FROM UserAccount u " +
            "LEFT JOIN FETCH u.roles r " +
            "LEFT JOIN FETCH r.rolePermissions rp " +
            "LEFT JOIN FETCH rp.permission " +
            "WHERE u.username = :username")
    Optional<UserAccount> findByUsernameWithRolesAndPermissions(@Param("username") String username);
}

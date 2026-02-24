package com.tmdt.base.domain.repository;

import com.tmdt.base.domain.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    Page<UserAccount> findUsers(
            String displayname,
            String address,
            Long roleId,
            Boolean isActive,
            Pageable pageable
    );

    Optional<UserAccount> findUserByIdAndIsActiveTrue(Long id);
}

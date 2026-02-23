package com.tmdt.base.domain.repository.auth;

import com.tmdt.base.domain.model.UserAccount;

import java.util.Optional;

public interface UserAccountRepository {
    Optional<UserAccount> findByUsername(String username);

    Optional<UserAccount> findByUsernameWithRolesAndPermissions(String username);

    UserAccount save(UserAccount userAccount);
}

package com.tmdt.base.infrastructure.persistence.adapter;

import com.tmdt.base.domain.model.UserAccount;
import com.tmdt.base.domain.repository.auth.UserAccountRepository;
import com.tmdt.base.infrastructure.persistence.jpa.JpaUserAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {

    private final JpaUserAccountRepository jpaRepository;

    public UserAccountRepositoryImpl(JpaUserAccountRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return jpaRepository.findByUsername(username);
    }

    @Override
    public Optional<UserAccount> findByUsernameWithRolesAndPermissions(String username) {
        return jpaRepository.findByUsernameWithRolesAndPermissions(username);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return jpaRepository.save(userAccount);
    }
}

package com.tmdt.base.infrastructure.persistence.adapter;

import com.tmdt.base.domain.model.UserAccount;
import com.tmdt.base.domain.repository.UserRepository;
import com.tmdt.base.infrastructure.persistence.jpa.JpaUserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }


    @Override
    public Page<UserAccount> findUsers(String displayname, String address, Long roleId, Boolean isActive, Pageable pageable) {
        Specification<UserAccount> specification = (root, query, cb) ->
        {
            Predicate predicate = cb.conjunction();
            if (displayname != null && !displayname.isBlank()) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("displayname")),
                                "%" + displayname.toLowerCase() + "%"));
            }

            if (address != null && !address.isBlank()) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("address")),
                                "%" + address.toLowerCase() + "%"));
            }

            if (roleId != null && roleId != 0L) {
                predicate = cb.and(predicate, cb.equal(root.get("role"), roleId));
            }

            if (isActive != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("isActive"), isActive));
            }


            predicate = cb.and(predicate,
                    cb.equal(root.get("isDeleted"), false));

            return predicate;
        };

        return jpaUserRepository.findAll(specification, pageable);

    }

    @Override
    public Optional<UserAccount> findUserByIdAndIsActiveTrue(Long id) {
        return jpaUserRepository.findByIdAndIsActiveTrueWithRoles(id);
    }
}

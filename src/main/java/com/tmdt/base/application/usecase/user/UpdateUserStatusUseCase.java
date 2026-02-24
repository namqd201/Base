package com.tmdt.base.application.usecase.user;

import com.tmdt.base.domain.model.UserAccount;
import com.tmdt.base.domain.repository.UserRepository;
import com.tmdt.base.domain.repository.auth.UserAccountRepository;
import com.tmdt.base.shared.config.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateUserStatusUseCase {

    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;

    public UpdateUserStatusUseCase(UserRepository userRepository, UserAccountRepository userAccountRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public void execute(Long id, Boolean isActive) {
        UserAccount user = userRepository.findUserByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        user.setIsActive(isActive != null ? isActive : true);
        user.setUpdatedAt(LocalDateTime.now());

        // persist change using UserAccountRepository
        userAccountRepository.save(user);
    }
}

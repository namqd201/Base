package com.tmdt.base.application.usecase.auth.implimentations;

import com.tmdt.base.application.usecase.auth.interfaces.LoginUseCase;
import com.tmdt.base.domain.model.UserAccount;
import com.tmdt.base.domain.repository.auth.UserAccountRepository;
import com.tmdt.base.presentation.services.JwtService;
import com.tmdt.base.shared.config.exception.InvalidCredentialException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUseCaseImpl(
            UserAccountRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String login(String username, String password) {
        UserAccount user = repository.findByUsername(username)
                .orElseThrow(InvalidCredentialException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialException();
        }

        return jwtService.generateToken(user);

    }
}

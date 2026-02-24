package com.tmdt.base.presentation.controller;

import com.tmdt.base.application.usecase.auth.interfaces.LoginUseCase;
import com.tmdt.base.presentation.dto.request.auth.LoginRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "0. Auth Controller", description = "API for authentication")
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequest request) {

        String token = loginUseCase.login(
                request.getUsername(),
                request.getPassword()
        );

        return ResponseEntity.ok(token);
    }
}
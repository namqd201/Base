package com.tmdt.base.shared.config.exception;

public class RoleNameAlreadyExistsException extends RuntimeException {

    public RoleNameAlreadyExistsException(String roleName) {
        super("Role name already exists: " + roleName);
    }
}


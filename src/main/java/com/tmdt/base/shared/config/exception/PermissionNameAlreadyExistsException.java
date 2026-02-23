package com.tmdt.base.shared.config.exception;

public class PermissionNameAlreadyExistsException extends RuntimeException {

    public PermissionNameAlreadyExistsException(String permissionName) {
        super("Permission name already exists: " + permissionName);
    }
}

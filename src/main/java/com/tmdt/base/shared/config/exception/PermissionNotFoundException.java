package com.tmdt.base.shared.config.exception;

public class PermissionNotFoundException extends RuntimeException{
    public PermissionNotFoundException(Long id) {
        super("Permission not found with id: " + id);
    }
}

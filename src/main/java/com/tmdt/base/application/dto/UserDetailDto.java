package com.tmdt.base.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDetailDto {
    private Long id;
    private String username;
    private String displayName;
    private String email;
    private String address;
    private String phoneNumber;
    private String avatar;
    private Boolean isActive;
    private List<RoleDto> roles;
}

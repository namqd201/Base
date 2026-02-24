package com.tmdt.base.application.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListUserDto {
    private Long id;
    private String username;
    private String displayName;
    private String email;
    private String address;
    private String phoneNumber;
    private String avatar;
}

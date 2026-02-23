package com.tmdt.base.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import com.tmdt.base.application.dto.PermissionDto;

@Getter
@Setter
public class RoleDto extends BaseDto {
    private Long id;
    private String roleName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Long createdBy;
    private Long updatedBy;
    private Long deletedBy;
    private Boolean isActive;
    private Boolean isDeleted;
    private List<PermissionDto> permissions;
}


package com.tmdt.base.presentation.dto.request.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateRoleRequest {

    @NotBlank
    @Size(max = 50)
    private String roleName;

    private List<Long> permissionIds;
}


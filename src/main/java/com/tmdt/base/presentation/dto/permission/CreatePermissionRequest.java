package com.tmdt.base.presentation.dto.permission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermissionRequest {

    @NotBlank(message = "Permission name is required")
    @Size(max = 200)
    private String permissionName;
}

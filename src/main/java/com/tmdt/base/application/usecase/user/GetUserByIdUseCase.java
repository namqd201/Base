package com.tmdt.base.application.usecase.user;

import com.tmdt.base.application.dto.UserDetailDto;
import com.tmdt.base.domain.model.UserAccount;
import com.tmdt.base.domain.repository.UserRepository;
import com.tmdt.base.shared.config.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GetUserByIdUseCase {

    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailDto execute(Long id) {
        UserAccount user = userRepository.findUserByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        return mapToUserDetailDto(user);
    }

    private UserDetailDto mapToUserDetailDto(UserAccount user) {
        UserDetailDto dto = new UserDetailDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setDisplayName(user.getDisplayName());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAvatar(user.getAvatar());
        dto.setIsActive(user.getIsActive());
        
        // Map roles (filter out soft-deleted roles)
        dto.setRoles(
            user.getUserRoles().stream()
                .map(ur -> ur.getRole())
                .filter(role -> role != null && !role.getIsDeleted())
                .map(role -> {
                    com.tmdt.base.application.dto.RoleDto roleDto = new com.tmdt.base.application.dto.RoleDto();
                    roleDto.setId(role.getId());
                    roleDto.setRoleName(role.getRoleName());
                    return roleDto;
                })
                .collect(Collectors.toList())
        );

        return dto;
    }
}

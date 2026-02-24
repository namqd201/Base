package com.tmdt.base.application.usecase.user;

import com.tmdt.base.application.dto.ListUserDto;
import com.tmdt.base.domain.model.UserAccount;
import com.tmdt.base.domain.repository.UserRepository;
import com.tmdt.base.presentation.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class GetUsersUseCase {

    private final UserRepository userRepository;

    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<ListUserDto> execute(
            String displayName,
            String address,
            Long roleId,
            Boolean isActive,
            int page,
            int size,
            String sortBy,
            boolean ascending
    ) {

        Sort sort = ascending
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UserAccount> users = userRepository.findUsers(
                displayName,
                address,
                roleId,
                isActive,
                pageable
        );

        return users.map(UserMapper.INSTANCE::entityToDto);
    }
}

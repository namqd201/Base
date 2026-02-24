package com.tmdt.base.presentation.controller;

import com.tmdt.base.application.dto.ListUserDto;
import com.tmdt.base.application.dto.UserDetailDto;
import com.tmdt.base.application.usecase.user.GetUsersUseCase;
import com.tmdt.base.application.usecase.user.GetUserByIdUseCase;
import com.tmdt.base.application.usecase.user.UpdateUserStatusUseCase;
import com.tmdt.base.presentation.dto.user.UpdateUserStatusRequest;
import com.tmdt.base.shared.response.MessageDto;
import com.tmdt.base.shared.response.SingleDataResponse;
import com.tmdt.base.presentation.dto.response.PageResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "3. User Controller", description = "API for user")
@RequestMapping("/users")
public class UserController {

    private final GetUsersUseCase getUsersUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UpdateUserStatusUseCase updateUserStatusUseCase;

    public UserController(GetUsersUseCase getUsersUseCase, GetUserByIdUseCase getUserByIdUseCase, UpdateUserStatusUseCase updateUserStatusUseCase) {
        this.getUsersUseCase = getUsersUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.updateUserStatusUseCase = updateUserStatusUseCase;
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<UserDetailDto> getUserById(@PathVariable Long id) {
        UserDetailDto user = getUserByIdUseCase.execute(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ResponseEntity<SingleDataResponse<MessageDto>> updateUserStatus(@PathVariable Long id, @RequestBody UpdateUserStatusRequest request) {
        updateUserStatusUseCase.execute(id, request.getIsActive());
        MessageDto messageDto = new MessageDto("USU002");
        return ResponseEntity.ok().body(new SingleDataResponse<>(messageDto));
    }

    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PreAuthorize("hasAuthority('USER_READ')")
    public PageResponse<ListUserDto> getUsers(

            @RequestParam(required = false) String displayName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Long roleId,
            @RequestParam(required = false) Boolean isActive,

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "false") boolean ascending
    ) {

        Page<ListUserDto> result = getUsersUseCase.execute(
                displayName,
                address,
                roleId,
                isActive,
                page,
                size,
                sortBy,
                ascending
        );

        return PageResponse.from(result);
    }
}

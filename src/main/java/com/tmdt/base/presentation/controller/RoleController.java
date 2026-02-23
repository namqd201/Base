package com.tmdt.base.presentation.controller;

import com.tmdt.base.application.dto.RoleDto;
import com.tmdt.base.application.usecase.role.*;
import com.tmdt.base.domain.model.Role;
import com.tmdt.base.presentation.dto.role.CreateRoleRequest;
import com.tmdt.base.presentation.dto.role.UpdateRoleRequest;
import com.tmdt.base.shared.response.MessageDto;
import com.tmdt.base.shared.response.SingleDataResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "2. Role Controller", description = "API for roles")
@RequestMapping("/roles")
public class RoleController {

    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;
    private final GetRoleByIdUseCase getRoleByIdUseCase;
    private final GetAllRoleUseCase getAllRoleUseCase;

    public RoleController(
            CreateRoleUseCase createRoleUseCase,
            UpdateRoleUseCase updateRoleUseCase,
            DeleteRoleUseCase deleteRoleUseCase,
            GetRoleByIdUseCase getRoleByIdUseCase,
            GetAllRoleUseCase getAllRoleUseCase
    ) {
        this.createRoleUseCase = createRoleUseCase;
        this.updateRoleUseCase = updateRoleUseCase;
        this.deleteRoleUseCase = deleteRoleUseCase;
        this.getRoleByIdUseCase = getRoleByIdUseCase;
        this.getAllRoleUseCase = getAllRoleUseCase;
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    public ResponseEntity<SingleDataResponse<MessageDto>> create(@Valid @RequestBody CreateRoleRequest request) {
        createRoleUseCase.create(request.getRoleName(), request.getPermissionIds());
        MessageDto messageDto = new MessageDto("RLC001");
        return ResponseEntity.ok().body(new SingleDataResponse<>(messageDto));
    }

    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    public ResponseEntity<SingleDataResponse<MessageDto>> update(@PathVariable Long id, @Valid @RequestBody UpdateRoleRequest request) {
        updateRoleUseCase.update(id, request.getRoleName(), request.getPermissionIds());
        MessageDto messageDto = new MessageDto("RLU002");
        return ResponseEntity.ok().body(new SingleDataResponse<>(messageDto));
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public ResponseEntity<SingleDataResponse<MessageDto>> delete(@PathVariable Long id) {
        deleteRoleUseCase.execute(id);
        MessageDto messageDto = new MessageDto("RLD003");
        return ResponseEntity.ok().body(new SingleDataResponse<>(messageDto));
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PreAuthorize("hasAuthority('ROLE_READ')")
    public ResponseEntity<SingleDataResponse<RoleDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new SingleDataResponse<>(getRoleByIdUseCase.execute(id)));
    }

    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PreAuthorize("hasAuthority('ROLE_READ')")
    public ResponseEntity<List<RoleDto>> getAll() {
        List<RoleDto> roles = getAllRoleUseCase.findAll();
        return ResponseEntity.ok(roles);
    }
}


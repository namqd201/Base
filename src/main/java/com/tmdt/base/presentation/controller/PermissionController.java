package com.tmdt.base.presentation.controller;

import com.tmdt.base.application.usecase.permission.*;
import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.presentation.dto.permission.CreatePermissionRequest;
import com.tmdt.base.presentation.dto.permission.UpdatePermissionRequest;
import com.tmdt.base.shared.response.AllPageResponse;
import com.tmdt.base.shared.response.PageResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "1. Permission Controller", description = "API for permissions")
@RequestMapping("/permissions")
public class PermissionController {

    private final CreatePermissionUseCase createUseCase;
    private final UpdatePermissionUseCase updateUseCase;
    private final DeletePermissionUseCase deleteUseCase;
    private final GetPermissionByIdUseCase getByIdUseCase;
    private final GetAllPermissionUseCase getAllUseCase;

    public PermissionController(
            CreatePermissionUseCase createUseCase,
            UpdatePermissionUseCase updateUseCase,
            DeletePermissionUseCase deleteUseCase,
            GetPermissionByIdUseCase getByIdUseCase,
            GetAllPermissionUseCase getAllUseCase
    ) {
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.getAllUseCase = getAllUseCase;
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<Permission> create(@Valid @RequestBody CreatePermissionRequest request) {
        Permission created = createUseCase.execute(request.getPermissionName());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public Permission update(@PathVariable Long id, @Valid @RequestBody UpdatePermissionRequest request) {
        return updateUseCase.execute(id, request.getPermissionName());
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public Permission getById(@PathVariable Long id) {
        return getByIdUseCase.execute(id);
    }

    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<AllPageResponse<Permission>> getAll() {
        List<Permission> findAll = getAllUseCase.findAll();
        return ResponseEntity.ok().body(new AllPageResponse<>(findAll, findAll.size()));
    }
}

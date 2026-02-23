package com.tmdt.base.presentation.controller;

import com.tmdt.base.application.dto.PermissionDto;
import com.tmdt.base.application.usecase.permission.*;
import com.tmdt.base.domain.model.Permission;
import com.tmdt.base.presentation.dto.permission.CreatePermissionRequest;
import com.tmdt.base.presentation.dto.permission.UpdatePermissionRequest;
import com.tmdt.base.shared.response.MessageDto;
import com.tmdt.base.shared.response.SingleDataResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SingleDataResponse<MessageDto>> create(@Valid @RequestBody CreatePermissionRequest request) {
        createUseCase.execute(request.getPermissionName());
        MessageDto messageDto = new MessageDto("PMC001");
        return ResponseEntity.ok().body(new SingleDataResponse<>(messageDto));
    }

    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public Permission update(@PathVariable Long id, @Valid @RequestBody UpdatePermissionRequest request) {
        return updateUseCase.updatePermission(id, request.getPermissionName());
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        deleteUseCase.execute(id);
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
    public ResponseEntity<List<PermissionDto>> getAll() {
        List<PermissionDto> findAll = getAllUseCase.findAll();
        return ResponseEntity.ok(findAll);
    }
}

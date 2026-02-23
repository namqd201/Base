package com.tmdt.base.presentation.mapper;

import com.tmdt.base.application.dto.RoleDto;
import com.tmdt.base.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.tmdt.base.presentation.mapper.PermissionMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PermissionMapper.class})
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleDto entityToDto(Role entity);
}


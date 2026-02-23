package com.tmdt.base.presentation.mapper;

import com.tmdt.base.application.dto.PermissionDto;
import com.tmdt.base.domain.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);
    PermissionDto entityToDto(Permission entity);
}

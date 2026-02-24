package com.tmdt.base.presentation.mapper;

import com.tmdt.base.application.dto.ListUserDto;
import com.tmdt.base.domain.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PermissionMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    ListUserDto entityToDto(UserAccount entity);
}

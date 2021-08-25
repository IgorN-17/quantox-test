package com.igor.www.user.conversion;

import java.util.List;

import com.igor.www.user.dto.UserDto;
import com.igor.www.user.entity.UserEntity;

public interface UserConversionService {

	UserEntity dtoToEntity(UserDto dto);
	
	UserDto entityToDto(UserEntity entity);
	
	List<UserEntity> dtosToEntities(List<UserDto> dtos);
	
	List<UserDto> entitiesToDtos(List<UserEntity> entities);
}

package com.igor.www.user.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.www.machine.conversion.MachineConversionService;
import com.igor.www.machine.dto.MachineDto;
import com.igor.www.machine.entity.MachineEntity;
import com.igor.www.user.dto.UserDto;
import com.igor.www.user.entity.UserEntity;

@Service
public class UserConversionServiceImpl implements UserConversionService {

	@Autowired
	private MachineConversionService machineConversionService;
	
	@Override
	public UserEntity dtoToEntity(UserDto dto) {
		UserEntity entity = new UserEntity();
		
		entity.setId(dto.getId());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		
		for (MachineDto machine : dto.getMachines()) {
			entity.getMachines().add(machineConversionService.dtoToEntity(machine));
		}
		
		return entity;
	}

	@Override
	public UserDto entityToDto(UserEntity entity) {
		UserDto dto = new UserDto();
		
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		// password and salt should not be retrieved
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		
		for (MachineEntity machine : entity.getMachines()) {
			dto.getMachines().add(machineConversionService.entityToDto(machine));
		}
			
		return dto;
	}

	@Override
	public List<UserEntity> dtosToEntities(List<UserDto> dtos) {
		final List<UserEntity> entities = new ArrayList<>();
		
		for (UserDto dto : dtos) {
			entities.add(dtoToEntity(dto));
		}
		
		return entities;
	}

	@Override
	public List<UserDto> entitiesToDtos(List<UserEntity> entities) {
		final List<UserDto> dtos = new ArrayList<>();
		
		for (UserEntity entity : entities) {
			dtos.add(entityToDto(entity));
		}
		
		return dtos;
	}

}

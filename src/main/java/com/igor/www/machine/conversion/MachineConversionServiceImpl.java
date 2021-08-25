package com.igor.www.machine.conversion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.igor.www.machine.dto.MachineDto;
import com.igor.www.machine.entity.MachineEntity;
import com.igor.www.user.entity.UserEntity;

@Service
public class MachineConversionServiceImpl implements MachineConversionService {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public MachineEntity dtoToEntity(MachineDto dto) {
		MachineEntity entity = new MachineEntity();
		
		entity.setId(dto.getId());
		entity.setUuid(dto.getUuid());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setStatus(dto.getStatus());
		entity.setOwner(entityManager.find(UserEntity.class, dto.getOwnerId()));
		
		return entity;
	}

	@Override
	public MachineDto entityToDto(MachineEntity entity) {
		MachineDto dto = new MachineDto();
		
		dto.setId(entity.getId());
		dto.setUuid(entity.getUuid());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setStatus(entity.getStatus());
		dto.setOwnerId(entity.getOwner().getId());
		
		return dto;
	}

	@Override
	public List<MachineEntity> dtosToEntities(List<MachineDto> dtos) {
		final List<MachineEntity> entities = new ArrayList<>();
		
		for (MachineDto dto : dtos) {
			entities.add(dtoToEntity(dto));
		}
		
		return entities;
	}

	@Override
	public List<MachineDto> entitiesToDtos(List<MachineEntity> entities) {
		final List<MachineDto> dtos = new ArrayList<>();
		
		for (MachineEntity entity : entities) {
			dtos.add(entityToDto(entity));
		}
		
		return dtos;
	}

}

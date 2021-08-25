package com.igor.www.machine.conversion;

import java.util.List;

import com.igor.www.machine.dto.MachineDto;
import com.igor.www.machine.entity.MachineEntity;

public interface MachineConversionService {

	MachineEntity dtoToEntity(MachineDto dto);
	
	MachineDto entityToDto(MachineEntity entity);
	
	List<MachineEntity> dtosToEntities(List<MachineDto> dtos);
	
	List<MachineDto> entitiesToDtos(List<MachineEntity> entities);
}

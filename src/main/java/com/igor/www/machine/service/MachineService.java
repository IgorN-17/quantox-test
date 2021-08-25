package com.igor.www.machine.service;

import java.util.List;

import com.igor.www.machine.dto.MachineDto;
import com.igor.www.machine.enums.Status;

public interface MachineService {

	MachineDto getMachineByUuid(String uuid);
	
	MachineDto startMachine(String uuid);
	
	MachineDto stopMachine(String uuid);
	
	MachineDto restartMachine(String uuid);
	
	MachineDto createMachine(MachineDto machine);
	
	MachineDto updateMachine(MachineDto machine);
	
	void destroyMachine(String uuid);
	
	List<MachineDto> searchByStatus(Status status);
}

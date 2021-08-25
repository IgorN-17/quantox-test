package com.igor.www.machine.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.www.machine.conversion.MachineConversionService;
import com.igor.www.machine.dto.MachineDto;
import com.igor.www.machine.entity.MachineEntity;
import com.igor.www.machine.enums.Status;
import com.igor.www.machine.repository.MachineRepository;

@Service
public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachineRepository machineRepository;
	
	@Autowired
	private MachineConversionService machineConversionService;

	@Override
	public MachineDto startMachine(String uuid) {
		MachineDto machine = getMachineByUuid(uuid);
		if (!machine.getStatus().equals(Status.STOPPED)) {
			throw new RuntimeException("Machine is already running!");
		}
		
		int simulateExcetionDuration = getRandomNumberInMilisForSecondsRange(10, 15);
		sleepRandomBetweenSeconds(simulateExcetionDuration);
		
		machine.setStatus(Status.RUNNING);
		
		updateMachine(machine);
		
		
		return machine;
	}

	@Override
	public MachineDto stopMachine(String uuid) {
		MachineDto machine = getMachineByUuid(uuid);
		if (!machine.getStatus().equals(Status.RUNNING)) {
			throw new RuntimeException("Machine is already stopped!");
		}
		
		int simulateExcetionDuration = getRandomNumberInMilisForSecondsRange(5, 10);
		sleepRandomBetweenSeconds(simulateExcetionDuration);
		
		machine.setStatus(Status.STOPPED);
		
		updateMachine(machine);
		
		return machine;
	}

	@Override
	public MachineDto restartMachine(String uuid) {
		MachineDto machine = getMachineByUuid(uuid);
		if (!machine.getStatus().equals(Status.RUNNING)) {
			throw new RuntimeException("Machine is already stopped!");
		}
		
		// restart = stop + start
		stopMachine(uuid);
		
		return startMachine(uuid);
	}

	@Override
	public MachineDto createMachine(MachineDto machine) {
		MachineEntity entity = machineConversionService.dtoToEntity(machine);
		entity.setStatus(Status.STOPPED);
		entity.setUuid(UUID.randomUUID().toString());
		entity.setCreatedAt(LocalDateTime.now());
		
		return machineConversionService.entityToDto(machineRepository.save(entity));
	}
	
	@Override
	public MachineDto updateMachine(MachineDto machine) {
		if (machine.getId() == null) {
			MachineDto machineFromDb = getMachineByUuid(machine.getUuid());
			machine.setId(machineFromDb.getId());
		}
		MachineEntity entity = machineConversionService.dtoToEntity(machine);
		
		return machineConversionService.entityToDto(machineRepository.save(entity));
	}

	@Override
	public void destroyMachine(String uuid) {
		MachineDto machine = getMachineByUuid(uuid);
		if (!machine.getStatus().equals(Status.STOPPED)) {
			throw new RuntimeException("Machine can not be destroyed! Please, stop machine first.");
		}
		
		machine.setActive(false);
		
		updateMachine(machine);

		machineRepository.deleteById(machine.getId());
	}

	@Override
	public List<MachineDto> searchByStatus(Status status) {
		List<MachineEntity> result = machineRepository.findAllByStatusAndActive(status, true);
		
		return machineConversionService.entitiesToDtos(result);
	}

	@Override
	public MachineDto getMachineByUuid(String uuid) {
		MachineEntity machine = machineRepository.findByUuid(uuid);
		return machineConversionService.entityToDto(machine);
	}
	
	private int getRandomNumberInMilisForSecondsRange(int startInSeconds, int endInSeconds) {
		return ThreadLocalRandom.current().nextInt(startInSeconds * 1000, endInSeconds * 1000);
	}

	private void sleepRandomBetweenSeconds(int duration) {
		 try {
			 Thread.sleep(duration);
		 } catch (Exception e) {
			 
		 }
	}

}

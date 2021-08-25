package com.igor.www.machine.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igor.www.machine.dto.MachineDto;
import com.igor.www.machine.enums.Status;
import com.igor.www.machine.service.MachineService;

@CrossOrigin
@RestController
@RequestMapping("/v1/machines")
public class MachineController {

	private static final Set<String> PROCESSING_MACHINES = ConcurrentHashMap.newKeySet();

	@Autowired
	private MachineService machineService;
	
	@PostMapping("/start")
    public ResponseEntity<MachineDto> start(@RequestParam(value = "uuid") String uuid) {
		checkIfMachineCanTryToChangeState(uuid);
		
		try {
			acquireMachine(uuid);
				
	        final MachineDto dto = machineService.startMachine(uuid);
	        
	        return new ResponseEntity<>(dto, HttpStatus.OK);
		} finally {
			releaseMachine(uuid);
		}
    }
	
	@PostMapping("/stop")
    public ResponseEntity<MachineDto> stop(@RequestParam(value = "uuid") String uuid) {
		checkIfMachineCanTryToChangeState(uuid);

		try {
			acquireMachine(uuid);
			
	        final MachineDto dto = machineService.stopMachine(uuid);
	        return new ResponseEntity<>(dto, HttpStatus.OK);
		} finally {
			releaseMachine(uuid);
		}
    }
	
	@PostMapping("/restart")
    public ResponseEntity<MachineDto> restart(@RequestParam(value = "uuid") String uuid) {
		checkIfMachineCanTryToChangeState(uuid);

		try {
	        final MachineDto dto = machineService.restartMachine(uuid);
	        
	        return new ResponseEntity<>(dto, HttpStatus.OK);
		} finally {
			releaseMachine(uuid);
		}
	}
	
	@PostMapping
    public ResponseEntity<MachineDto> create(@RequestBody MachineDto dto) {
        final MachineDto newMachine = machineService.createMachine(dto);
        return new ResponseEntity<>(newMachine, HttpStatus.OK);
    }
	
	@PostMapping("/destroy")
    public ResponseEntity<Void> destroy(@RequestParam(value = "uuid") String uuid) {
		checkIfMachineCanTryToChangeState(uuid);

		try {
			acquireMachine(uuid);
			
	        machineService.destroyMachine(uuid);
	        
	        return new ResponseEntity<>(HttpStatus.OK);
		} finally {
			releaseMachine(uuid);
		}
    }
	
	@GetMapping("/search")
    public ResponseEntity<List<MachineDto>> search(@RequestParam(name = "status", defaultValue = "RUNNING") Status status) {
        final List<MachineDto> filtered = machineService.searchByStatus(status); // TODO - need to implement fetching by user
        return new ResponseEntity<>(filtered, HttpStatus.OK);
    }
	
	private synchronized void checkIfMachineCanTryToChangeState(String uuid) {
		if (PROCESSING_MACHINES.contains(uuid)) {
			throw new RuntimeException(String.format("Machine with uuid %s is currently changing state. Please try later.", uuid));
		}
	}
	
	private synchronized void acquireMachine(String uuid) {
		PROCESSING_MACHINES.add(uuid);
	}
	
	private synchronized void releaseMachine(String uuid) {
		PROCESSING_MACHINES.remove(uuid);
	}
}

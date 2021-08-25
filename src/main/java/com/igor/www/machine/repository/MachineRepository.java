package com.igor.www.machine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.www.machine.entity.MachineEntity;
import com.igor.www.machine.enums.Status;

public interface MachineRepository extends JpaRepository<MachineEntity, Long> {

	MachineEntity findByUuid(String uuid);
	
	List<MachineEntity> findAllByStatusAndActive(Status status, boolean active);
}

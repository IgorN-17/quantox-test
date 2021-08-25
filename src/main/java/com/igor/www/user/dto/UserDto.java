package com.igor.www.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.igor.www.machine.dto.MachineDto;

public class UserDto {
	
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private List<MachineDto> machines = new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public List<MachineDto> getMachines() {
		return machines;
	}
	public void setMachines(List<MachineDto> machines) {
		this.machines = machines;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}

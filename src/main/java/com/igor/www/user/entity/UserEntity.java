package com.igor.www.user.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.igor.www.machine.entity.MachineEntity;

@Entity
@Table(name = "user_entity")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false, length = 100)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "salt", length = 100)
	private String salt;
	
	@Column(name = "firstName", length = 100)
	private String firstName;
	
	@Column(name = "lastName", length = 100)
	private String lastName;
	
	@OneToMany(mappedBy = "owner")
	private List<MachineEntity> machines = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
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

	public List<MachineEntity> getMachines() {
		return machines;
	}

	public void setMachines(List<MachineEntity> machines) {
		this.machines = machines;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}

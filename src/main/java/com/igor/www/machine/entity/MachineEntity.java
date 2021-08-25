package com.igor.www.machine.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.igor.www.machine.enums.Status;
import com.igor.www.user.entity.UserEntity;

@Entity
@Table(name = "machine_entity")
public class MachineEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "uuid", nullable = false)
	private String uid;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinColumn(name = "createdBy", nullable = false)
	private UserEntity owner;

	@Column(name = "createdAt", nullable = false)
	private LocalDateTime createdAt;
	
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}

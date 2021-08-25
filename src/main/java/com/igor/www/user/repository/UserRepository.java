package com.igor.www.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.www.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}

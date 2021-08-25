package com.igor.www.user.service;

import com.igor.www.user.dto.UserDto;

public interface UserService {
	
	UserDto getUserById(Long id);
	
	UserDto createUser(UserDto user);
	
}

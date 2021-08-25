package com.igor.www.user.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.www.user.conversion.UserConversionService;
import com.igor.www.user.dto.UserDto;
import com.igor.www.user.entity.UserEntity;
import com.igor.www.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConversionService userConversionService;

	@Override
	public UserDto getUserById(Long id) {
		UserEntity user = userRepository.getById(id);

		return userConversionService.entityToDto(user);
	}

	@Override
	public UserDto createUser(UserDto dto) {
		UserEntity user = userConversionService.dtoToEntity(dto);
		// encrypt password
		user.setSalt(BCrypt.gensalt());
		user.setPassword(BCrypt.hashpw(dto.getPassword(), user.getSalt()));
		UserEntity newUser = userRepository.save(user);
		
		return userConversionService.entityToDto(newUser);
	}

}

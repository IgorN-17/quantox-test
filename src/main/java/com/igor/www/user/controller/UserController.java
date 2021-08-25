package com.igor.www.user.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.www.user.dto.UserDto;
import com.igor.www.user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/{id}")
    public ResponseEntity<UserDto> create(@PathParam(value = "id") Long id) {
        final UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        final UserDto newUser = userService.createUser(dto);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}

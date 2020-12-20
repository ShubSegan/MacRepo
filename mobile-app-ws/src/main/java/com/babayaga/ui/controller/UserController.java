package com.babayaga.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babayaga.service.UserService;
import com.babayaga.shared.dto.UserDto;
import com.babayaga.ui.model.request.UserDetailsRequestModel;
import com.babayaga.ui.model.response.UserDetailsResponseModel;

@RestController 
@RequestMapping("users")//http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	@GetMapping
	public String getUser() {
		return "get user details";
	}
	
	@PutMapping
	public String putUser() {
		return "put user";
	}
	
	@PostMapping
	public UserDetailsResponseModel postUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserDto userdto = new UserDto();
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(userDetails,userdto);
		UserDto createdUser = userService.createUser(userdto);
		
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "Delete User";
	}
}

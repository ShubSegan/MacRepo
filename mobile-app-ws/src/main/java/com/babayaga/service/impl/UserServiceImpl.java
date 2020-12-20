package com.babayaga.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babayaga.repository.UserRepository;
import com.babayaga.service.UserService;
import com.babayaga.shared.dto.UserDto;
import com.babayaga.ui.entity.UserEntity;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
	@Override
	public UserDto createUser(UserDto userdto) {
		
		UserEntity duplicateEntry = userRepo.findByEmail(userdto.getEmail());
		if(userRepo.findByEmail(userdto.getEmail()) != null) throw	new RuntimeException("User already exists");
		
		
		UserDto returnValue = new UserDto();
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userdto, userEntity);
		userEntity.setEncryptedPassword("test encrypted password");
		userEntity.setUserId("test userId");
		UserEntity ue = userRepo.save(userEntity);
		
		BeanUtils.copyProperties(ue, returnValue);
		return returnValue;
	}

}

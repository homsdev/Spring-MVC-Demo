package com.homsdev.app.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.homsdev.app.domain.User;
import com.homsdev.app.domain.repository.UserRepository;
import com.homsdev.app.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void registerUser(User newUser) {
		
		String ID=UUID.randomUUID().toString();
		newUser.setUserID(ID);
		newUser.setEnable(true);
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		System.out.println("New User was created with: "+newUser.toString());
		
		userRepository.registerUser(newUser);
	}

}

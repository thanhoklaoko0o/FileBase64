package com.cuong.phonestore.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuong.phonestore.model.User;
import com.cuong.phonestore.repository.UserRepository;
import com.cuong.phonestore.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public User finUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.isPresent() ? (User) user.get() : null;
	}

}

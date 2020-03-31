package com.cuong.phonestore.services.impl;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuong.phonestore.model.User;
import com.cuong.phonestore.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = (userRepository.findByUsername(username)).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		return UserPrinciple.build(user);
	}
	 /*public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
        		user.getUsername(), user.getPassword(), emptyList());
    }*/
	
	@Transactional
	public User getUserFromPrinciple(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return null;
        }
        Optional<User> user = userRepository.findByUsername(principal.getName());//principal.getName()
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user.isPresent() ? (User) user.get() : null;
    }
}
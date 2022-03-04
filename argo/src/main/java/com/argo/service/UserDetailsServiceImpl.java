package com.argo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.argo.repository.ProductUserRepository;
import com.argo.user.ProductUser;
import com.argo.user.details.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ProductUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		ProductUser user = userRepository.getUserByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new MyUserDetails(user);
	}

}

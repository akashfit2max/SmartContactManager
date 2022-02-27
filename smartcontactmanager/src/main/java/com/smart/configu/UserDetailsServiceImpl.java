package com.smart.configu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.MyUserRepository;
import com.smart.entities.MyUser;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MyUserRepository myUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		fetching user by database

		MyUser user = myUserRepository.getMyUserByUserName(username);

		if (user == null) {

			throw new UsernameNotFoundException("could not found the user !!");
		}

		CustomUserDetails customUserDetails = new CustomUserDetails(user);

		return customUserDetails;
	}

}

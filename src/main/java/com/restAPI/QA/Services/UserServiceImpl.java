package com.restAPI.QA.Services;


import com.restAPI.QA.Entities.UserProfile;
import com.restAPI.QA.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserProfile userProfile = userRepository.findByUsername(username);
		if(userProfile == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		//Assign role to user
		//https://stackoverflow.com/questions/37615034/spring-security-spring-boot-how-to-set-roles-for-users
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userProfile.getRole().toString()));


		return new org.springframework.security.core.userdetails.User(userProfile.getUsername(), userProfile.getPassword(), grantedAuthorities);
	}





	/*
	 * https://docs.spring.io/spring-security/site/docs/4.0.2.RELEASE/reference/htmlsingle/#obtaining-information-about-the-current-user
	 * Get current logged user object
	 * */
	@Override
	public UserProfile getCurrentUser() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}

		return userRepository.findByUsername(username);
	}



	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}






}

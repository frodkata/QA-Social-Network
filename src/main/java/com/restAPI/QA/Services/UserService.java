package com.restAPI.QA.Services;

import com.restAPI.QA.Entities.UserProfile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
	UserProfile getCurrentUser();
	void deleteUserById(Long id);

}

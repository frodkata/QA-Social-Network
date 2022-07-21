package com.restAPI.QA.Repositories;

import com.restAPI.QA.Entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

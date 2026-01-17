package com.homehub.core.login.repo;

import com.homehub.core.login.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends JpaRepository<Users, Long> {
    Users findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}

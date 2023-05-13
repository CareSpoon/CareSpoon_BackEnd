package com.carespoon.user.repository;

import com.carespoon.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByUuid(String uuid);
    User findByEmail(String email);
}

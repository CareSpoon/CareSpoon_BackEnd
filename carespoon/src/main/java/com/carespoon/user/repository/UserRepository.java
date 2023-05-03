package com.carespoon.user.repository;

import com.carespoon.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByUuid(String uuid);
    User findByEmail(String email);
}

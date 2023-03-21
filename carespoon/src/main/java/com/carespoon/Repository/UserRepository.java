package com.carespoon.Repository;

import com.carespoon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(UUID uuid);
}

package com.carespoon.Repository;

import com.carespoon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUUID();
}

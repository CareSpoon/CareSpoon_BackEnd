package com.carespoon.Repository;

import com.carespoon.domain.SeniorUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeniorUserRepository extends JpaRepository<SeniorUser, Long> {
}

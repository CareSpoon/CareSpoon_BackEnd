package com.carespoon.Repository;

import com.carespoon.domain.OneMeal;
import com.carespoon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;


public interface OneMealRepository extends JpaRepository<OneMeal, Long> , OneMealRepositoryCustom{
    List<OneMeal> findByUser(User user);

}

package com.carespoon.oneMeal.repository;

import com.carespoon.oneMeal.domain.OneMeal;
import com.carespoon.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface OneMealRepository extends JpaRepository<OneMeal, Long> , OneMealRepositoryCustom{
    List<OneMeal> findByUser(User user);

    @Query("select o from OneMeal o where o.user = ?1 and o.eatDate = ?2")
    Optional<List<OneMeal>> findOneMealByEatDate(User user, String eatDate);

    @Query("select o from OneMeal o where o.user = ?1 and o.eatMonth = ?2")
    Optional<List<OneMeal>> findOneMealByEatMonth(User user, String eatMonth);
}

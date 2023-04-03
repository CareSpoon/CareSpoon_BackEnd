package com.carespoon.menu.repository;

import com.carespoon.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuName(String menu);
}

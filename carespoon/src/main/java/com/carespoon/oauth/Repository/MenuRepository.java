package com.carespoon.Repository;

import com.carespoon.Menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuName(String menu);
}

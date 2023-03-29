package com.carespoon.Repository;

import com.carespoon.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByMenuName(List<String> Name);
}

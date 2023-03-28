package com.carespoon.service;

import com.carespoon.Repository.MenuRepository;
import com.carespoon.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    MenuRepository menuRepository;
    public Menu findByMenuName(String name){
        Menu menu = menuRepository.findByMenuName(name);
        return menu;
    }
}

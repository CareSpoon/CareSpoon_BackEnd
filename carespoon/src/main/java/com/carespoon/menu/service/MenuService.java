package com.carespoon.menu.service;

import com.carespoon.exception.ErrorStatus;
import com.carespoon.exception.model.NotMenuException;
import com.carespoon.menu.repository.MenuRepository;
import com.carespoon.menu.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    public List<Menu> findByMenuName(List<String> name) throws NotMenuException {
        List<Menu> menu = new ArrayList<>(name.size());
        for(int i = 0; i<name.size(); i++){
            menu.add(i, menuRepository.findByMenuName(name.get(i)).orElseThrow(() -> new NotMenuException(ErrorStatus.NOT_MENU_FIND_EXCEPTION, ErrorStatus.NOT_MENU_FIND_EXCEPTION.getMessage())));
        }
        return menu;
    }
}

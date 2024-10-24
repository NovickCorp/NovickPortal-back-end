package com.novick.customers.planner.services;

import com.novick.customers.planner.repositories.MenuRepository;
import com.novick.customers.planner.entities.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }
}

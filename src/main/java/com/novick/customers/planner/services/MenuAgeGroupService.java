package com.novick.customers.planner.services;

import com.novick.customers.planner.entities.MenuAgeGroup;
import com.novick.customers.planner.repositories.MenuAgeGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuAgeGroupService {

    private final MenuAgeGroupRepository menuAgeGroupRepository;

    public MenuAgeGroupService(MenuAgeGroupRepository menuAgeGroupRepository) {
        this.menuAgeGroupRepository = menuAgeGroupRepository;
    }

    public MenuAgeGroup save(MenuAgeGroup menuAgeGroup) {
        return menuAgeGroupRepository.save(menuAgeGroup);
    }
}

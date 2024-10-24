package com.novick.customers.planner.services;

import com.novick.customers.planner.entities.MenuSchedule;
import com.novick.customers.planner.repositories.MenuScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuScheduleService {

    private final MenuScheduleRepository menuScheduleRepository;

    public MenuScheduleService(MenuScheduleRepository menuScheduleRepository) {
        this.menuScheduleRepository = menuScheduleRepository;
    }

    public MenuSchedule save(MenuSchedule menuSchedule) {
        return menuScheduleRepository.save(menuSchedule);
    }
}

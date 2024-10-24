package com.novick.customers.planner.repositories;

import com.novick.customers.planner.entities.MenuSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuScheduleRepository extends JpaRepository<MenuSchedule, Integer> {
}

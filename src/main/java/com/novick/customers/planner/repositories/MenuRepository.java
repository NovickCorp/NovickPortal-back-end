package com.novick.customers.planner.repositories;

import com.novick.customers.planner.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}

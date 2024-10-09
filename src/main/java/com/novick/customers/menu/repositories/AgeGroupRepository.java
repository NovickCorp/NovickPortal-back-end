package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.AgeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeGroupRepository extends JpaRepository<AgeGroup, Integer> {
}

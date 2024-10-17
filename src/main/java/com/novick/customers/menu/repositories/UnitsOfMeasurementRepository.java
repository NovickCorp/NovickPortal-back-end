package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.UnitOfMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitsOfMeasurementRepository extends JpaRepository<UnitOfMeasurement, Integer> {
}

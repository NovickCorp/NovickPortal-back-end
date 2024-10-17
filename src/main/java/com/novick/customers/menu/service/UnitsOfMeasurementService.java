package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.UnitOfMeasurement;
import com.novick.customers.menu.repositories.UnitsOfMeasurementRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UnitsOfMeasurementService {

    private UnitsOfMeasurementRepository unitsOfMeasurementRepository;

    public UnitsOfMeasurementService(UnitsOfMeasurementRepository unitsOfMeasurementRepository) {
        this.unitsOfMeasurementRepository = unitsOfMeasurementRepository;
    }

    @Cacheable("uoms")
    public Map<Integer, String> unitsOfMeasurement() {
        return unitsOfMeasurementRepository.findAll().stream().collect(Collectors.toMap(UnitOfMeasurement::getId, UnitOfMeasurement::getName));
    }
}

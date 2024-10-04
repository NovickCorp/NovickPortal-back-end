package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.ServingSize;
import com.novick.customers.menu.repositories.ServingSizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServingSizeService {

    private ServingSizeRepository servingSizeRepository;

    public ServingSizeService(ServingSizeRepository servingSizeRepository) {
        this.servingSizeRepository = servingSizeRepository;
    }

    public List<ServingSize> findAll() {
        return servingSizeRepository.findAll();
    }

}

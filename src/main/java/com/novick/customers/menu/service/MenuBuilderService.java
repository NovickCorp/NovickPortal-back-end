package com.novick.customers.menu.service;

import com.novick.customers.menu.repositories.ServingSizeRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuBuilderService {

    private ServingSizeRepository repository;

    public MenuBuilderService(ServingSizeRepository repository) {
        this.repository = repository;
    }

}

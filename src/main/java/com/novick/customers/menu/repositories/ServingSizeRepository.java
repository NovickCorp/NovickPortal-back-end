package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.ServingSize;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServingSizeRepository extends JpaRepository<ServingSize, Integer> {

    @Cacheable("items")
    List<ServingSize> findAll();

}

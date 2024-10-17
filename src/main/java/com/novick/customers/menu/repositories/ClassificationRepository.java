package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassificationRepository extends JpaRepository<Classification, Integer> {

    List<Classification> findAllById(int id);
}

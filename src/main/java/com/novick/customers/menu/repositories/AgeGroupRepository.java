package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.AgeGroup;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface AgeGroupRepository extends JpaRepository<AgeGroup, Integer> {

    @Cacheable("ageGroups")
    @NonNull List<AgeGroup> findAll(@NonNull Sort sort);

}

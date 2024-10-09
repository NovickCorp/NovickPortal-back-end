package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.AgeGroup;
import com.novick.customers.menu.repositories.AgeGroupRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AgeGroupService {

    private final AgeGroupRepository ageGroupRepository;

    public AgeGroupService(AgeGroupRepository ageGroupRepository) {
        this.ageGroupRepository = ageGroupRepository;
    }

    @Cacheable("ageGroups")
    public List<AgeGroup> findAll() {
        return ageGroupRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    public Map<Integer, AgeGroup> ageGroupMap() {
        return findAll().stream().collect(Collectors.toMap(AgeGroup::getId, Function.identity()));
    }
}

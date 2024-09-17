package com.novick.customers.landing.repositories;

import com.novick.customers.landing.entities.PromoSpace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoSpaceRepository extends CrudRepository<PromoSpace, Long> {
}

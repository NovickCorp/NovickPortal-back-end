package com.novick.customers.landing.repositories;

import com.novick.customers.landing.entities.CopyrightLinks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyrightLinksRepository extends CrudRepository<CopyrightLinks, Long> {
}

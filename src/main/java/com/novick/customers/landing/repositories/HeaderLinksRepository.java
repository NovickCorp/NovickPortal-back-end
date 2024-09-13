package com.novick.customers.landing.repositories;

import com.novick.customers.landing.entities.HeaderLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderLinksRepository extends JpaRepository<HeaderLinks, Long> {
}

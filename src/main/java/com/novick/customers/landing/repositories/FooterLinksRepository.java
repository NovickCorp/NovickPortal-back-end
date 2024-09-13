package com.novick.customers.landing.repositories;

import com.novick.customers.landing.entities.FooterLinks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterLinksRepository extends CrudRepository<FooterLinks, Long> {
}

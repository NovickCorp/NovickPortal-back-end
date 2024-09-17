package com.novick.customers.landing.repositories;

import com.novick.customers.landing.entities.Footer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterRepository extends CrudRepository<Footer, Long> {
}

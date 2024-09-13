package com.novick.customers.landing.repositories;

import com.novick.customers.landing.entities.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsInterface extends CrudRepository<News, Integer> {
}

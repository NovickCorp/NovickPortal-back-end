package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.CreditableTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditableTableRepository extends JpaRepository<CreditableTable, Integer> {
}

package org.openlab.openlabcustomerservice.repositories;

import org.openlab.openlabcustomerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
}

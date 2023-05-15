package de.chatgpt.sample.customerapp.repositories;
import org.springframework.data.repository.CrudRepository;

import de.chatgpt.sample.customerapp.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}

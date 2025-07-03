package com.example.customerapi.demo.repository;

import com.example.customerapi.demo.dto.CustomerDTO;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepository {
    private final Map<Long, CustomerDTO> database = new HashMap<>();
    private Long idCounter = 1L;

    public CustomerDTO save(CustomerDTO customer) {
        if (customer.getId() == null) {
            customer.setId(idCounter++);
        }
        database.put(customer.getId(), customer);
        return customer;
    }

    public Optional<CustomerDTO> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public List<CustomerDTO> findAll() {
        return new ArrayList<>(database.values());
    }

    public void delete(Long id) {
        database.remove(id);
    }

    public boolean existsById(Long id) {
        return database.containsKey(id);
    }
}

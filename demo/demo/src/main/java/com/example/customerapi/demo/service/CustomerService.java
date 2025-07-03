package com.example.customerapi.demo.service;

import com.example.customerapi.demo.dto.CustomerDTO;
import com.example.customerapi.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerDTO create(CustomerDTO customer) {
        return repository.save(customer);
    }

    public Optional<CustomerDTO> getById(Long id) {
        return repository.findById(id);
    }

    public List<CustomerDTO> getAll() {
        return repository.findAll();
    }

    public Optional<CustomerDTO> update(Long id, CustomerDTO customer) {
        if (!repository.existsById(id)) return Optional.empty();
        customer.setId(id);
        return Optional.of(repository.save(customer));
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.delete(id);
        return true;
    }
}

package com.arka.arkajuanprenss.domain.port.out;

import com.arka.arkajuanprenss.domain.model.Customer;

import java.util.List;
import java.util.Optional;
public interface CustomerRepositoryPort {
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
    Customer update(Customer customer);
    void deleteById(Long id);
    boolean existsById(Long id);
}

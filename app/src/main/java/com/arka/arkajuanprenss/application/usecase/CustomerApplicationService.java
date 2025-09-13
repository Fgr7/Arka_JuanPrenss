package com.arka.arkajuanprenss.application.usecase;

import com.arka.arkajuanprenss.domain.model.Customer;
import com.arka.arkajuanprenss.domain.port.in.CustomerUseCase;
import com.arka.arkajuanprenss.domain.port.out.CustomerRepositoryPort;

import java.util.List;
import java.util.Optional;

public class CustomerApplicationService implements CustomerUseCase {

    private final CustomerRepositoryPort customerRepository;

    public CustomerApplicationService(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.orElseThrow(() -> 
            new RuntimeException("Cliente con ID " + id + " no encontrado"));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (!customer.isValidEmail()) {
            throw new RuntimeException("Email inválido para el cliente");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("❌ Cliente no encontrado con ID: " + id);
        }
        customer.setId(id); // aseguramos que el ID del path se use
        return customerRepository.update(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
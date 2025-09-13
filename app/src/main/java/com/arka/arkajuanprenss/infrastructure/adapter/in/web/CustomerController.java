package com.arka.arkajuanprenss.infrastructure.adapter.in.web;
import com.arka.arkajuanprenss.domain.model.Customer;
import com.arka.arkajuanprenss.domain.port.in.CustomerUseCase;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.CustomerDTO;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper.CustomerWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {
    
    private final CustomerUseCase customerUseCase;
    private final CustomerWebMapper webMapper;

    public CustomerController(CustomerUseCase customerUseCase, CustomerWebMapper webMapper) {
        this.customerUseCase = customerUseCase;
        this.webMapper = webMapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerUseCase.getAllCustomers();
        return ResponseEntity.ok(webMapper.toDTO(customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerUseCase.getCustomerById(id);
        return ResponseEntity.ok(webMapper.toDTO(customer));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO dto) {
        Customer customer = webMapper.toDomain(dto);
        Customer saved = customerUseCase.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(webMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        Customer customer = webMapper.toDomain(dto);
        Customer updated = customerUseCase.updateCustomer(id, customer);
        return ResponseEntity.ok(webMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerUseCase.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}

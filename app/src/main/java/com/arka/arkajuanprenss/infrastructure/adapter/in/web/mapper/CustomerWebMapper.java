package com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper;
import com.arka.arkajuanprenss.domain.model.Customer;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerWebMapper {
      public CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;
        return new CustomerDTO(
                customer.getId(),
                customer.getNombre(),
                customer.getEmail(),
                customer.getTelefono(),
                customer.getCiudad(),
                customer.getPais()
        );
    }

    public Customer toDomain(CustomerDTO dto) {
        if (dto == null) return null;
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setNombre(dto.getNombre());
        customer.setEmail(dto.getEmail());
        customer.setTelefono(dto.getTelefono());
        customer.setCiudad(dto.getCiudad());
        customer.setPais(dto.getPais());
        return customer;
    }

    public List<CustomerDTO> toDTO(List<Customer> customers) {
        return customers.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

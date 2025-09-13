package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.mapper;
import com.arka.arkajuanprenss.domain.model.Customer;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CustomerJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerPersistenceMapper {
       public CustomerJpaEntity toEntity(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerJpaEntity entity = new CustomerJpaEntity();
        entity.setId(customer.getId());
        entity.setNombre(customer.getNombre());
        entity.setEmail(customer.getEmail());
        entity.setTelefono(customer.getTelefono());
        entity.setCiudad(customer.getCiudad());
        entity.setPais(customer.getPais());
        return entity;
    }

    public Customer toDomain(CustomerJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setNombre(entity.getNombre());
        customer.setEmail(entity.getEmail());
        customer.setTelefono(entity.getTelefono());
        customer.setCiudad(entity.getCiudad());
        customer.setPais(entity.getPais());
        return customer;
    }
}

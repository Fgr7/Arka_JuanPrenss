package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence;
import com.arka.arkajuanprenss.domain.model.Customer;
import com.arka.arkajuanprenss.domain.port.out.CustomerRepositoryPort;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CustomerJpaEntity;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.mapper.CustomerPersistenceMapper;
import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.repository.CustomerJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerPersistenceAdapter implements CustomerRepositoryPort {
      private final CustomerJpaRepository customerJpaRepository;
    private final CustomerPersistenceMapper mapper;

    public CustomerPersistenceAdapter(CustomerJpaRepository customerJpaRepository,
                                      CustomerPersistenceMapper mapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity entity = mapper.toEntity(customer);
        CustomerJpaEntity savedEntity = customerJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        customerJpaRepository.deleteById(id);
    }

    @Override
    public Customer update(Customer customer) {
        if (customer.getId() == null) {
            throw new IllegalArgumentException("El cliente debe tener ID para actualizarse");
        }
        CustomerJpaEntity entity = mapper.toEntity(customer);
        CustomerJpaEntity updatedEntity = customerJpaRepository.save(entity);
        return mapper.toDomain(updatedEntity);
    }
    
    @Override
    public boolean existsById(Long id) {
        return customerJpaRepository.existsById(id);
    }
}

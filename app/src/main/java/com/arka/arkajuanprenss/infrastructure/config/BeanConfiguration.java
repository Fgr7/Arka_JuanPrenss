package com.arka.arkajuanprenss.infrastructure.config;
import com.arka.arkajuanprenss.domain.port.in.*;
import com.arka.arkajuanprenss.domain.port.out.*;
import com.arka.arkajuanprenss.application.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public ProductUseCase productUseCase(ProductRepositoryPort productRepository, 
                                       CategoryRepositoryPort categoryRepository) {
        return new ProductApplicationService(productRepository, categoryRepository);
    }

    @Bean
    public CategoryUseCase categoryUseCase(CategoryRepositoryPort categoryRepository,
                                        DomainEventPublisher eventPublisher) {
        return new CategoryApplicationService(categoryRepository, eventPublisher);
    }
    
    @Bean
    public CustomerUseCase customerUseCase(CustomerRepositoryPort customerRepository) {
        return new CustomerApplicationService(customerRepository);
    }
}

package com.arka.arkajuanprenss.domain.port.out;

public interface DomainEventPublisher {
    void publish(Object event); 
}

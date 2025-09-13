package com.arka.arkajuanprenss.application.usecase;


import com.arka.arkajuanprenss.domain.model.Order;
import com.arka.arkajuanprenss.domain.port.in.OrderUseCase;
import com.arka.arkajuanprenss.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderApplicationService implements OrderUseCase {
    private final OrderRepositoryPort orderRepository;

    public OrderApplicationService(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public Order createOrder(Order order) {
        if (!order.isValid()) {
            throw new IllegalArgumentException("La orden no es válida.");
        }
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("La orden con ID " + id + " no existe.");
        }
        if (!order.isValid()) {
            throw new IllegalArgumentException("La orden no es válida.");
        }
        order.setId(id);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("La orden con ID " + id + " no existe.");
        }
        orderRepository.deleteById(id);
    }
}

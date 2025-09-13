package com.arka.arkajuanprenss.infrastructure.adapter.in.web;

import com.arka.arkajuanprenss.application.usecase.OrderApplicationService;
import com.arka.arkajuanprenss.domain.model.Order;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.OrderDTO;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper.OrderWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
     private final OrderApplicationService orderService;

    public OrderController(OrderApplicationService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders()
                .stream()
                .map(OrderWebMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(OrderWebMapper.toDTO(order));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = OrderWebMapper.toDomain(orderDTO);
        Order created = orderService.createOrder(order);
        return ResponseEntity.ok(OrderWebMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = OrderWebMapper.toDomain(orderDTO);
        Order updated = orderService.updateOrder(id, order);
        return ResponseEntity.ok(OrderWebMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

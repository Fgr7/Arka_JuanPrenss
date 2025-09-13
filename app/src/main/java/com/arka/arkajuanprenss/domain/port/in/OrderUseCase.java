package com.arka.arkajuanprenss.domain.port.in;

import com.arka.arkajuanprenss.domain.model.Order;
import java.util.List;

public interface OrderUseCase {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order createOrder(Order order);

    Order updateOrder(Long id, Order order);

    void deleteOrder(Long id); 

}

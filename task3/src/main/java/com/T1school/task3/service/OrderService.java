package com.T1school.task3.service;

import com.T1school.task3.entity.Order;
import com.T1school.task3.entity.User;
import com.T1school.task3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public Order createOrder(String description,String status, Long userId) {
        Order order = new Order(description,status);
        User user=userService.getUser(userId);
        order.setUser(user);
        orderRepository.save(order);
        return order;
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
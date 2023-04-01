package com.example.cw7.service;

import com.example.cw7.dao.OrderDAO;
import com.example.cw7.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    public void placeOrder(Long clientId, Long dishId) {
        orderDAO.placeOrder(clientId, dishId);
    }
}

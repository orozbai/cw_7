package com.example.cw7.service;

import com.example.cw7.dao.OrderDAO;
import com.example.cw7.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    public void placeOrder(Long clientId, Long dishId) {
        orderDAO.placeOrder(clientId, dishId);
    }

    public List<OrderDTO> getOrdersByClientId(Long id) {
        return orderDAO.getOrdersByClientId(id);
    }
}

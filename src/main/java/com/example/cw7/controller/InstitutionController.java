package com.example.cw7.controller;

import com.example.cw7.dto.*;
import com.example.cw7.entity.Client;
import com.example.cw7.entity.Dish;
import com.example.cw7.entity.Institution;
import com.example.cw7.security.SecurityConfig;
import com.example.cw7.service.ClientService;
import com.example.cw7.service.DishService;
import com.example.cw7.service.InstitutionService;
import com.example.cw7.service.OrderService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/institution")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private DishService dishService;

    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<InstitutionDTO> getInstitution() {
        return institutionService.findAll();
    }

    @GetMapping("/{institutionId}/dishes")
    public List<DishDTO> getDishesByInstitution(@PathVariable Long institutionId) {
        return dishService.getDishesByInstitution(institutionId);
    }

    @PostMapping("/{institutionId}/dishes/{dishId}/order")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long institutionId, @PathVariable Long dishId) {
        var email = SecurityConfig.getCurrentUserEmail();
        Client client = clientService.getByEmail(email).get(0);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        Institution institution = institutionService.getInstitutionById(institutionId);
        if (institution == null) {
            return ResponseEntity.notFound().build();
        }

        Dish dish = dishService.getDishById(dishId);
        Long id = dishService.getInstitutionId(dishId);
        if (dish == null || !Objects.equals(id, institutionId)) {
            return ResponseEntity.notFound().build();
        }

        OrderDTO order = new OrderDTO(client.getName(), client.getEmail(), dish.getName(), dish.getTypeDish(),
                dish.getPrice(), LocalDateTime.now());
        orderService.placeOrder(client.getId(), dish.getId());
        return ResponseEntity.ok(order);
    }

    @GetMapping("/client/orders")
    public List<OrderByClientDTO> getOrdersByClient() {
        Client client = clientService.getClientByEmail(SecurityConfig.getCurrentUserEmail());
        if (client == null) {
            return Collections.emptyList();
        }
        return orderService.getOrdersByClientId(client.getId());
    }
}

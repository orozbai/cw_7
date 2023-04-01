package com.example.cw7.controller;

import com.example.cw7.dto.DishDTO;
import com.example.cw7.dto.InstitutionDTO;
import com.example.cw7.entity.Client;
import com.example.cw7.entity.Dish;
import com.example.cw7.entity.Institution;
import com.example.cw7.entity.Order;
import com.example.cw7.security.SecurityConfig;
import com.example.cw7.service.ClientService;
import com.example.cw7.service.DishService;
import com.example.cw7.service.InstitutionService;
import com.example.cw7.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<Order> placeOrder(@PathVariable Long institutionId, @PathVariable Long dishId) {
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
        var id = dishService.getInstitutionId(dishId);
        if (dish == null || !Objects.equals(id, institutionId)) {
            return ResponseEntity.notFound().build();
        }

        Order order = new Order(client.getId(), client, dish, LocalDateTime.now());
        orderService.placeOrder(client.getId(), dish.getId());
        return ResponseEntity.ok(order);
    }
}

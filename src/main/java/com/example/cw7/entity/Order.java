package com.example.cw7.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private Client client;
    private Dish orderedDish;
    private LocalDateTime orderDate;
}

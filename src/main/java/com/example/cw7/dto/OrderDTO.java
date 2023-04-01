package com.example.cw7.dto;

import com.example.cw7.entity.Client;
import com.example.cw7.entity.Dish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Client client;
    private Dish orderedDish;
    private LocalDateTime orderDate;
}

package com.example.cw7.dto;

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
    private String clientName;
    private String email;
    private String name;
    private String typeDish;
    private Long price;
    private LocalDateTime orderDate;
}

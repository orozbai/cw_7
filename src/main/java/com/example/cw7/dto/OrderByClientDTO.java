package com.example.cw7.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderByClientDTO {
    private String dishName;
    private String typeDish;
    private Long price;
    private String fromInstitutionName;
    private LocalDateTime orderDate;
}

package com.example.cw7.util;

import com.example.cw7.dao.DishDAO;
import com.example.cw7.dao.InstitutionDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDatabase {
    @Bean
    CommandLineRunner init(InstitutionDAO institutionDAO, DishDAO dishDAO) {
        return (args) -> {
            institutionDAO.createTable();
            dishDAO.createTable();
        };
    }
}
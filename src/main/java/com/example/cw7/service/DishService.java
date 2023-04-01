package com.example.cw7.service;

import com.example.cw7.dao.DishDAO;
import com.example.cw7.dto.DishDTO;
import com.example.cw7.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishDAO dishDAO;

    public List<DishDTO> getDishesByInstitution(Long institutionId) {
        return dishDAO.getDishesByInstitution(institutionId);
    }

    public Dish getDishById(Long dishId) {
        return dishDAO.getDishById(dishId);
    }

    public Long getInstitutionId(Long dishId) {
        return dishDAO.getInstitutionId(dishId);
    }
}

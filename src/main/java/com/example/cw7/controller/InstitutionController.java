package com.example.cw7.controller;

import com.example.cw7.dto.DishDTO;
import com.example.cw7.dto.InstitutionDTO;
import com.example.cw7.service.DishService;
import com.example.cw7.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/institution")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private DishService dishService;

    @GetMapping()
    public List<InstitutionDTO> getInstitution() {
        return institutionService.findAll();
    }

    @GetMapping("/{institutionId}/dishes")
    public List<DishDTO> getDishesByInstitution(@PathVariable Long institutionId) {
        return dishService.getDishesByInstitution(institutionId);
    }
}

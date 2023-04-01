package com.example.cw7.service;

import com.example.cw7.dao.InstitutionDAO;
import com.example.cw7.dto.InstitutionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionService {
    @Autowired
    private InstitutionDAO institutionDAO;

    public List<InstitutionDTO> findAll() {
        return institutionDAO.findAll();
    }
}

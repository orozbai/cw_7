package com.example.cw7.service;

import com.example.cw7.dao.ClientDAO;
import com.example.cw7.dto.ClientDTO;
import com.example.cw7.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientDAO clientDAO;

    public List<Client> getByEmail(String email) {
        return clientDAO.getByEmail(email);
    }

    public Client getClientByEmail(String currentUserEmail) {
        return clientDAO.getClientByEmail(currentUserEmail);
    }

    public void registerClient(ClientDTO clientDTO) {
        clientDAO.registerClient(clientDTO);
    }

    public boolean existsByEmail(String email) {
        return clientDAO.existsByEmail(email);
    }
}

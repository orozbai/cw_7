package com.example.cw7.controller;

import com.example.cw7.dto.ClientDTO;
import com.example.cw7.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody ClientDTO client) {
        if (clientService.existsByEmail(client.getEmail())) {
            return new ResponseEntity<>("Email already used", HttpStatus.BAD_REQUEST);
        }
        clientService.registerClient(client);
        return new ResponseEntity<>("Register successfully", HttpStatus.OK);
    }
}

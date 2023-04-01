package com.example.cw7.dao;

import com.example.cw7.entity.Client;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDAO extends BaseDAO {
    public ClientDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {

    }

    public List<Client> getByEmail(String email) {
        String sql = "SELECT * FROM client WHERE email LIKE '" + email + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Client.class));
    }
}

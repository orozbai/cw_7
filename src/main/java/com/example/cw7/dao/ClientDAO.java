package com.example.cw7.dao;

import com.example.cw7.dto.ClientDTO;
import com.example.cw7.entity.Client;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDAO extends BaseDAO {
    public ClientDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS client (" +
                "id SERIAL PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "password TEXT NOT NULL);" +
                "INSERT INTO client (name, email, password) \n" +
                "VALUES \n" +
                "('guest', 'guest@gmail.com', '" + new BCryptPasswordEncoder().encode("guest") + "')," +
                "('admin', 'admin@gmail.com', '" + new BCryptPasswordEncoder().encode("admin") + "')," +
                "('guest', 'guest', '" + new BCryptPasswordEncoder().encode("guest") + "');");
    }

    public List<Client> getByEmail(String email) {
        String sql = "SELECT * FROM client WHERE email LIKE '" + email + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Client.class));
    }

    public Client getClientByEmail(String currentUserEmail) {
        String sql = "SELECT * FROM client WHERE email LIKE '" + currentUserEmail + "'";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Client.class));
    }

    public void registerClient(ClientDTO clientDTO) {
        jdbcTemplate.execute("INSERT INTO client (name, email, password) \n" +
                "VALUES \n" +
                "('" + clientDTO.getName() + "', '" + clientDTO.getEmail() + "'," +
                "'" + new BCryptPasswordEncoder().encode(clientDTO.getPassword()) + "');");
    }

    public String getEmail(String email) {
        String sql = "SELECT * FROM client WHERE email LIKE '" + email + "'";
        try {
            return jdbcTemplate.queryForObject(sql, String.class);
        } catch (EmptyResultDataAccessException e) {
            return " ";
        }
    }

    public boolean existsByEmail(String email) {
        return getEmail(email).contains(email);
    }
}

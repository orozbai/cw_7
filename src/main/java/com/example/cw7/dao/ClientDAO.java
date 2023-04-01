package com.example.cw7.dao;

import com.example.cw7.dto.ClientDTO;
import com.example.cw7.entity.Client;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
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
                "('guest', 'guest@gmail.com', 'guest')," +
                "('admin', 'admin@gmail.com', 'admin')," +
                "('guest', 'guest', 'guest')");
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
        String sql = "INSERT INTO client (name, email, password)" +
                "VALUES (?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, clientDTO.getName());
            ps.setString(2, clientDTO.getEmail());
            ps.setString(3, clientDTO.getPassword());
            return ps;
        });
    }
}

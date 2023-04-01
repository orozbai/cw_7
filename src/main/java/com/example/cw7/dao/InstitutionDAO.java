package com.example.cw7.dao;

import com.example.cw7.dto.InstitutionDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstitutionDAO extends BaseDAO {
    public InstitutionDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<InstitutionDTO> findAll() {
        String sql = "SELECT name, description FROM institution";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(InstitutionDTO.class));
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS institution(" +
                "id SERIAL PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "description TEXT );" +
                "INSERT INTO institution (name, description) \n" +
                "VALUES \n" +
                "('papa Carlos pizzeria', 'discount for 2 pizzas')," +
                "('Joni sushi', 'delicious sushi')," +
                "('Marry fast food', 'delicious burgers')");
    }
}

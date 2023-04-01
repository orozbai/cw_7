package com.example.cw7.dao;

import com.example.cw7.dto.DishDTO;
import com.example.cw7.entity.Dish;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DishDAO extends BaseDAO {
    public DishDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS dish (" +
                "id SERIAL PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "typedish TEXT NOT NULL," +
                "price INTEGER NOT NULL," +
                "institution_id INTEGER NOT NULL" +
                "FOREIGN KEY (institution_id) REFERENCES institution(id) ON DELETE CASCADE );" +
                "INSERT INTO dish (name, typedish, price, institution_id) \n" +
                "VALUES \n" +
                "('margarita' , 'pizza', 5, 1)," +
                "('macarello', 'pizza', 8, 1)," +
                "('assorti', 'pizza', 4, 1)," +
                "('10 yami', 'sushi', 10, 2)," +
                "('samurai', 'sushi', 15, 2)," +
                "('hot sushi', 'sushi', 8, 2)," +
                "('big burger', 'burger', 3, 3)," +
                "('cheese burger', 'burger', 6, 3)," +
                "('chicken burger', 'burger', 7, 3);");
    }

    public List<DishDTO> getDishesByInstitution(Long institutionId) {
        String sql = "SELECT name, typedish, price FROM dish WHERE institution_id = " + institutionId;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DishDTO.class));
    }

    public Dish getDishById(Long dishId) {
        String sql = "SELECT * FROM dish WHERE id = " + dishId;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Dish.class));
    }

    public Long getInstitutionId(Long dishId) {
        String sql = "SELECT institution_id FROM institution WHERE id = " + dishId;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Long.class));
    }
}

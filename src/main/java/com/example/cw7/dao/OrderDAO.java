package com.example.cw7.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class OrderDAO extends BaseDAO {
    public OrderDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public void placeOrder(Long clientId, Long dishId) {
        String sql = "INSERT INTO orders (client, ordereddish, orderdate)" +
                "VALUES(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, clientId);
            ps.setLong(2, dishId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS orders (" +
                "id SERIAL PRIMARY KEY," +
                "client INTEGER NOT NULL," +
                "ordereddish INTEGER NOT NULL," +
                "orderdate TIMESTAMP NOT NULL," +
                "FOREIGN KEY (client) REFERENCES client(id) ON DELETE CASCADE," +
                "FOREIGN KEY (ordereddish) REFERENCES dish(id) ON DELETE CASCADE);" +
                "INSERT INTO orders (client, ordereddish, orderdate) \n" +
                "VALUES \n" +
                "(1, 1, '2018-06-22 19:10:25')," +
                "(2, 2, '2019-07-20 15:10:25')," +
                "(3, 3, '2020-03-22 18:10:25');");
    }
}

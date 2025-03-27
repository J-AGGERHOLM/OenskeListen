package org.example.oenskelisten.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;


    public WishRepository() {
        jdbcTemplate = new JdbcTemplate();
    }

}

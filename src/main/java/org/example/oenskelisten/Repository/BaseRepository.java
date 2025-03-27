package org.example.oenskelisten.Repository;

import org.springframework.jdbc.core.JdbcTemplate;

// Hvis man nu i fremtiden vil skifte database eller bruge et nyt bibliotek
// Gør det, det nemmere at skifte ud med en base class.
public class BaseRepository {
    private final JdbcTemplate jdbcTemplate;

    public BaseRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public JdbcTemplate getJdbc() {
        return jdbcTemplate;
    }
}

package org.example.oenskelisten.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository extends BaseRepository {

    public WishRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


}

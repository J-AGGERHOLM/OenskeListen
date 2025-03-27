package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Model.Person;
import org.example.oenskelisten.Model.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {


    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //get all users

    public List<Person> getAllUsers(){
        String sql = "SELECT * FROM persons";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }



}

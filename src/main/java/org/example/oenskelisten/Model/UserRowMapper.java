package org.example.oenskelisten.Model;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        int personId = rs.getInt("personID");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        return new User(personId,name,email,password);
    }
}

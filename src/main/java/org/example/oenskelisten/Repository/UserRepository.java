package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IUserRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //get all users
    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM persons";

        try {
            return jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getById(int id) {
        // vælger specifik user
        String sql = "SELECT * FROM persons " +
                "WHERE personId = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                    new UserRowMapper(),
                    id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean edit(User newUser) {
        // Opdater attraction
        String sql = "UPDATE persons " +
                "SET name = ?, email = ?, password = ? " +
                "WHERE personId = ?";

        try {
            return jdbcTemplate.update(sql, newUser.getName(),
                    newUser.getEmail(),
                    newUser.getPassword(),
                    newUser.getPersonId()) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void add(User newUser) {
        String sql = "INSERT INTO persons (name, email, password) VALUES (?,?,?)";
        jdbcTemplate.update(sql, newUser.getName(), newUser.getEmail(), newUser.getPassword());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM persons WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public User getByEmail(String email) {
        try {
        String sql = "SELECT * FROM persons " +
                "WHERE email = ?";
            return jdbcTemplate.queryForObject(sql,
                    new UserRowMapper(),
                    email);
        } catch (DataAccessException e) {
            return null;
        }
    }
}

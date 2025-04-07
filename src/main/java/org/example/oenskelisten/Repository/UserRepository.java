package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IUserRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //get all users
    @Override
    @Transactional
    public List<User> getAll() {
        String sql = "SELECT * FROM users";

        try {
            return jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    @Transactional
    public User getById(int id) {
        // vælger specifik user
        String sql = "SELECT users.userID,users.name, users.email, users.password, " +
                "GROUP_CONCAT(wishlist.wishlistID) AS wishlistID, " +
                "GROUP_CONCAT(wishlist.name) AS wishListName " +
                "FROM users " +
                "LEFT JOIN wishlist ON users.userID = wishlist.userID " +
                "WHERE users.userID = ? " +
                "GROUP BY users.userID";
        try {
            return jdbcTemplate.queryForObject(sql,
                    new UserRowMapper(),
                    id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean edit(User newUser) {
        // Opdater attraction
        String sql = "UPDATE users " +
                "SET name = ?, email = ?, password = ? " +
                "WHERE userID = ?";

        try {
            return jdbcTemplate.update(sql, newUser.getName(),
                    newUser.getEmail(),
                    newUser.getPassword(),
                    newUser.getUserID()) > 0;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    @Transactional
    public void add(User newUser) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
        try {
            jdbcTemplate.update(sql, newUser.getName(), newUser.getEmail(), newUser.getPassword());
        } catch (DataAccessException e) {
            throw new RuntimeException("Der er skete en fejl",e);
        }

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE userID = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Kunne ikke slette id: " + id, e);
        }

    }

    @Override
    public User getByEmail(String email) {
        try {
            String sql = "SELECT * FROM users " +
                    "WHERE email = ?";
            return jdbcTemplate.queryForObject(sql,
                    new UserRowMapper(),
                    email);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public int getUserIDByWishListID(int wishListID) {
        String sql = "SELECT wishlist.userID FROM wishlist WHERE wishlistID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, wishListID);
        } catch (DataAccessException e) {
            throw new RuntimeException("Kunne ikke finde id: " + wishListID,e);
        }


    }

}

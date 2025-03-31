package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IUserRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.UserRowMapper;
import org.example.oenskelisten.Model.WishList;
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
    @Transactional
    public List<User> getAll() {
        String sql = "SELECT * FROM persons";

        try {
            return jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
    }


    @Transactional
    public User getById(int id) {
        // vælger specifik user
        String sql = "SELECT " +
                "persons.personID," +
                "persons.name," +
                "persons.email," +
                "persons.password," +
                "GROUP_CONCAT(wishlist.wishlistID) AS wishlistID, " +
                "GROUP_CONCAT(wishlist.name) AS wishListName " +
                " FROM persons " +
                "LEFT JOIN wishlist ON persons.personID = wishlist.personID "+
                "WHERE persons.personId = ? " +
                "GROUP BY persons.personID"
                ;
        try {
            User foundUser = jdbcTemplate.queryForObject(sql,
                    new UserRowMapper(),
                    id);
            return foundUser;
        } catch (Exception e) {
            return null;
        }
    }




    @Transactional
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


    @Transactional
    public void add(User newUser) {
        String sql = "INSERT INTO persons (name, email, password) VALUES (?,?,?)";
        jdbcTemplate.update(sql, newUser.getName(), newUser.getEmail(), newUser.getPassword());
    }

    @Transactional
    public void deleteById(int id) {
        String sql = "DELETE FROM persons WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }


}

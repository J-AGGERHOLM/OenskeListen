package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IUserRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.UserRowMapper;
import org.example.oenskelisten.Model.WishList;
import org.example.oenskelisten.Model.WishListRowMapper;
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
            User foundUser = jdbcTemplate.queryForObject(sql,
                    new UserRowMapper(),
                    id);
            foundUser.setWishListList(findUserWishLists(id));
            return foundUser;
        } catch (Exception e) {
            return null;
        }
    }

        //Helper method for finding wishLists belonging to a user
    public List<WishList> findUserWishLists(int personID){
        String sql = "SELECT wishList.wishlistID , wishlist.name, wishList.personID FROM wishlist " +
                "JOIN persons ON wishlist.personID = persons.personID " +
                "WHERE wishList.personID = ? ";

        return jdbcTemplate.query(sql, new WishListRowMapper(), personID);


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
}

package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IUserRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends BaseRepository implements IUserRepository {

    public UserRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    //get all users
    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM persons";

        try{
        return getJdbc().query(sql, new UserRowMapper());}
        catch (Exception e){
            return null;
        }
    }

    @Override
    public User getById(int id) {
        // vælger specifik user
        String sql = "SELECT * FROM persons " +
                "WHERE personId = ?";
        try {
            return getJdbc().queryForObject(sql,
                    new UserRowMapper(),
                    id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean edit(User newUser) {
        // Opdater attraction
        String sql = "UPDATE persons " +
                "SET name = ?, email = ?, password = ? " +
                "WHERE personId = ?";

        try{
            return getJdbc().update(sql, newUser.getName(),
                    newUser.getEmail(),
                    newUser.getPassword(),
                    newUser.getPersonId()) > 0;
        } catch(Exception e) {
            return false;
        }
    }
}

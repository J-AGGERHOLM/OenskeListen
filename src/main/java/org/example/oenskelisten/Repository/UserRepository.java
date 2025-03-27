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
    public List<User> getAll(){
        String sql = "SELECT * FROM persons";
        return getJdbc().query(sql, new UserRowMapper());
    }

    // Get by id
    @Override
    public User getById(int id){
        String sql = "SELECT * FROM persons " +
                "WHERE personId = ?";
        return getJdbc().queryForObject(sql, new UserRowMapper(), id);
    }
}

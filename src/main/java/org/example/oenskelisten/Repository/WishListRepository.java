package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IRepository;
import org.example.oenskelisten.Interface.IWishRepository;
import org.example.oenskelisten.Model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository extends BaseRepository implements IRepository<Wish>, IWishRepository<Wish> {

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Wish> getAll() {
//        String sql ="SELECT name, description, productLink, imageLink, price FROM wishes";
//        return getJdbc().query(sql, new WishRowMapper());
        return null;
    }

    @Override
    public Wish getById(int id) {
//        String sql = "SELECT * FROM wishes WHERE id = ?";
//        try {
//            return getJdbc().queryForObject(sql, new WishRowMapper(), id);
//        } catch (Exception e){
//            return null;
//        }
        return null;
    }

    @Override
    public void add(Wish wish) {
//        String sql = "INSERT INTO wishes (name, description, productlink, imagelink, price ) VALUES (?, ?, ?, ?, ?)";
//        getJdbc().update(sql, wish.getName(),
//                              wish.getDescription(),
//                              wish.getProductLink(),
//                              wish.getImageLink(),
//                              wish.getPrice());
    }

    @Override
    public boolean edit(Wish wish) {
//        String sql = "UPDATE wishes SET name = ?, description = ?, productlink = ?, imagelink = ?, price = ?,
//                     WHERE id = ?";
//        getJdbc().update(sql, wish.getName(),
//                              wish.getDescription(),
//                              wish.getProductLink(),
//                              wish.getImageLink(),
//                              wish.getPrice());
        return false;
    }

    @Override
    public Wish getByName(String name) {
//        String sql = "SELECT * FROM wish WHERE name = ?";
//        try {
//            return getJdbc().queryForObject(sql, new WishRowMapper(), name);
//        } catch (Exception e){
//            return null;
//        }

        return null;
    }

    @Override
    public void deleteById(int id) {
//        String sql ="DELETE FROM wishes WHERE id = ?";
//        getJdbc().update(sql, id);

    }
}

package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IWishListRepository;
import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Model.WishRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository implements IWishListRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Wish> getAll() {
//        String sql ="SELECT name, description, productLink, imageLink, price FROM wishes";
//        return jdbcTemplate.query(sql, new WishRowMapper());
        return null;
    }

    @Override
    public Wish getById(int id) {
        System.out.println("We in the getbyID");
       String sql = "SELECT * FROM wishes WHERE wishID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new WishRowMapper(), id);
        } catch (Exception e){
            System.out.println("We done goofed boi");
            return null;
        }
    }

    @Override
    public void add(Wish wish) {
        String sql = "INSERT INTO wishes (name, description, productlink, imagelink, price, wishlistID, reserved, reserveeID ) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                wish.getName(),
                wish.getDescription(),
                wish.getProductLink(),
                wish.getImageLink(),
                wish.getPrice(),
                wish.getWishlistID(),
                wish.isReserved(),
                wish.getReserveeIDAsString());

    }

    @Override
    public boolean edit(Wish wish) {
        String sql = "UPDATE wishes SET name = ?, description = ?, productlink = ?, imagelink = ?, price = ? " +
                "WHERE wishID = ?";
        jdbcTemplate.update(sql, wish.getName(),
                              wish.getDescription(),
                              wish.getProductLink(),
                              wish.getImageLink(),
                              wish.getPrice(),
                                wish.getId());
        System.out.println();
        return true;
    }

    @Override
    public Wish getByName(String name) {
//        String sql = "SELECT * FROM wish WHERE name = ?";
//        try {
//            return jdbcTemplate.queryForObject(sql, new WishRowMapper(), name);
//        } catch (Exception e){
//            return null;
//        }

        return null;
    }

    @Override
    public void deleteById(int id) {
//        String sql ="DELETE FROM wishes WHERE id = ?";
//        jdbcTemplate.update(sql, id);

    }
}

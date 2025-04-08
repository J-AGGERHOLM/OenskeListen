package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IWishRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Model.WishRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class WishRepository implements IWishRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Wish> getAll() {
        System.out.println("hello from getall");
        //String sql ="SELECT name, description, productLink, imageLink, price FROM wishes";
        String sql = "SELECT * FROM wishes";
        return jdbcTemplate.query(sql, new WishRowMapper());
        //return null;
    }


    @Override
    public Wish getById(int id) {
        String sql = "SELECT * FROM wishes WHERE wishID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new WishRowMapper(), id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void add(Wish wish) {
        String sql = "INSERT INTO wishes (name, description, productlink, imagelink, price, wishlistID, reserved, reserveeID ) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                wish.getName(),
                wish.getDescription(),
                wish.getProductLink(),
                imagechecker(wish),
                wish.getPrice(),
                wish.getWishlistID(),
                wish.isReserved(),
                wish.getReserveeIDAsString());

    }

    @Override
    @Transactional
    public boolean edit(Wish wish) {
        String sql = "UPDATE wishes SET name = ?, description = ?, productlink = ?, imagelink = ?, price = ? " +
                "WHERE wishID = ?";
        jdbcTemplate.update(sql, wish.getName(),
                wish.getDescription(),
                wish.getProductLink(),
                wish.getImageLink(),
                wish.getPrice(),
                wish.getId());
        return true;
    }

    @Override
    public Wish getByName(String name) {
        String sql = "SELECT * FROM wishes WHERE name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new WishRowMapper(), name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Wish> getWishListById(int id) {
        String sql = "SELECT * FROM wishes WHERE wishes.wishListID = ?";
        try {
            return jdbcTemplate.query(sql, new WishRowMapper(), id);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM wishes WHERE wishID = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException("Kunne ikke slette id: " + id, e);
        }
    }

    @Override
    public void reserve(Wish wish, User user){
        String sql = "UPDATE Wishes\n" +
                "SET reserved = ?, reserveeID = ?\n" +
                "WHERE wishID = ?;";

        boolean reserve = true;
        if(wish.isReserved()){
            if(wish.getReserveeID() == user.getUserID()){
                reserve = false;
            }
        }

        try{
            jdbcTemplate.update(sql,reserve,user.getUserID(),wish.getId());
        }catch(Exception e){
            throw new RuntimeException("error with reserving wish");
        }

    }




    public String imagechecker(Wish wish) {
        String imageLink;

        if (wish.getImageLink().isBlank()
                || wish.getImageLink().isEmpty()
                || wish.getImageLink().equalsIgnoreCase("https://example.com/ereader.jpg" )
                || wish.getImageLink().length() > 4999) {
            imageLink = "https://images.template.net/75040/Free-Disney-Star-Vector-1.jpg";
        } else {
            imageLink = wish.getImageLink();
        }
        return imageLink;
    }
}

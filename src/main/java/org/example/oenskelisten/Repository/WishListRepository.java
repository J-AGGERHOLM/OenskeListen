package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IWishListRepository;
import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Model.WishList;
import org.example.oenskelisten.Model.WishListRowMapper;
import org.example.oenskelisten.Model.WishRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WishListRepository implements IWishListRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
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
        } catch (Exception e){
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
                wish.getImageLink(),
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
        String sql = "SELECT * FROM wish WHERE name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new WishRowMapper(), name);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Wish> getWishListById(int id) {
        String sql = "SELECT * FROM wishes WHERE wishes.wishListID = ?";

        return jdbcTemplate.query(sql, new WishRowMapper(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql ="DELETE FROM wishes WHERE wishID = ?";
        jdbcTemplate.update(sql, id);

    }

    //---------------WISHLIST METHODS----------------------
    @Override
    public List<WishList> getAllWishLists(){
        String sql = "SELECT * FROM wishlist";
        ArrayList allWishLists = new ArrayList<WishList>();

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()){
            WishList currentList = new WishList(
                    rowSet.getInt("wishlistID"),
                    rowSet.getString("name"),
                    rowSet.getInt("userID"));
            allWishLists.add(currentList);
        }
        return allWishLists;
    }

    @Override

    public WishList getWishListModelByID(int id){
        String sql = "SELECT \n" +
                "    wishlist.name AS wishlist_name, \n" +
                "    wishes.name AS wish_name, \n" +
                "    wishlist.*, \n" +
                "    wishes.*\n" +
                "FROM wishlist \n" +
                "LEFT JOIN wishes \n" +
                "ON wishlist.wishlistID = wishes.wishlistID \n" +
                "WHERE wishlist.wishlistID = ?;";
        return jdbcTemplate.queryForObject(sql, new WishListRowMapper(), id);

    }
}

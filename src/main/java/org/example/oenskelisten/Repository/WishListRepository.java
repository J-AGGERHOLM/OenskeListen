package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IWishListRepository;
import org.example.oenskelisten.Model.WishList;
import org.example.oenskelisten.Model.WishListRowMapper;
import org.springframework.dao.DataAccessException;
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
    public List<WishList> getAll() {
        String sql = "SELECT * FROM wishlist";
        ArrayList<WishList> allWishLists = new ArrayList<>();

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            WishList currentList = new WishList(
                    rowSet.getInt("wishlistID"),
                    rowSet.getString("name"),
                    rowSet.getInt("userID"));
            allWishLists.add(currentList);
        }
        return allWishLists;
    }

    @Override
    public WishList getById(int id) {
        String sql = "SELECT \n" +
                "    wishlist.name AS wishlist_name, \n" +
                "    wishes.name AS wish_name, \n" +
                "    wishlist.*, \n" +
                "    wishes.*\n" +
                "FROM wishlist \n" +
                "LEFT JOIN wishes \n" +
                "ON wishlist.wishlistID = wishes.wishlistID \n" +
                "WHERE wishlist.wishlistID = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, new WishListRowMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }


    }

    @Transactional
    @Override
    public void add(WishList wishList) {
        String sql = "INSERT INTO wishList(name, userID) VALUES (?,?)";
        try {
            jdbcTemplate.update(sql, wishList.getName(), wishList.getUserID());
        } catch (DataAccessException e) {
            throw new RuntimeException("Kunne ikke tilføje: " + wishList.getName(), e);
        }

    }

    @Transactional
    @Override
    public void deleteById(int wishListID) {
        String sql = "DELETE FROM wishList WHERE wishListID = ?";
        try {
            jdbcTemplate.update(sql, wishListID);
        } catch (DataAccessException e) {
            throw new RuntimeException("Kunne ikke slette: " + wishListID, e);
        }
    }
}

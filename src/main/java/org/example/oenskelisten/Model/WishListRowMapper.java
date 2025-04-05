package org.example.oenskelisten.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishListRowMapper implements RowMapper<WishList> {

    @Override
    public WishList mapRow(ResultSet rs, int rownum) throws SQLException {
        WishList wishList = new WishList(rs.getInt("wishlistID"), rs.getString("wishlist_name"), rs.getInt("userID"));
        List<Wish> wishes = new ArrayList<Wish>();
        //assuming that if wishes are emtpy we only have one row and the wish columns are null.
        //god help us if someone puts bad data in this somehow
        if (rs.getString("wishID") == null) {
            return wishList;
        }

        //now that we've checked that the wishlist isn't empty:
        wishes.add(mapWish(rs));
        while (rs.next()) {
            wishes.add(mapWish(rs));
        }
        wishList.setWishes(wishes);


        return wishList;


    }

    private Wish mapWish(ResultSet rs) throws SQLException {
        Wish wish = new Wish();
        wish.setId(rs.getInt("wishID"));
        wish.setName(rs.getString("wish_name"));
        wish.setDescription(rs.getString("description"));
        wish.setProductLink(rs.getString("productLink"));
        wish.setImageLink(rs.getString("imageLink"));
        wish.setPrice(rs.getInt("price"));
        wish.setWishlistID(rs.getInt("wishlistID"));
        wish.setReserved(rs.getBoolean("reserved"));
        wish.setReserveeID(rs.getInt("reserveeID"));
        return wish;

    }
}

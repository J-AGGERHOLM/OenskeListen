package org.example.oenskelisten.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {

    @Override
    public Wish mapRow(ResultSet rs, int rownum) throws SQLException {
        int wishID = rs.getInt("wishID");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String productLink = rs.getString("productLink");
        String imageLink = rs.getString("imageLink");
        int price = rs.getInt("price");
        int wishlistID = rs.getInt("wishlistID");
        boolean reserved = rs.getBoolean("reserved");
        int reserveeID = -1;
        if (reserved) {
            reserveeID = rs.getInt("reserveeID");
        }

        Wish wish = new Wish(wishID, name, description, productLink, imageLink, price, wishlistID, reserved, reserveeID);
        return wish;
    }
}

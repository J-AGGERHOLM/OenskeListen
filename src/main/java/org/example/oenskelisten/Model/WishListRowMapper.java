package org.example.oenskelisten.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishListRowMapper implements RowMapper<WishList> {
    @Override
    public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
        int personID = rs.getInt("personID");
                String name = rs.getString("name");
                int wishListID = rs.getInt("wishListID");
        return new WishList(wishListID,name,personID);

    }
}

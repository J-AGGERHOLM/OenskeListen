package org.example.oenskelisten.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        var user = new User();
        final int USER_ID = rs.getInt("userID");
        user.setUserID(USER_ID);
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        if (hasColumn(rs, "wishListName") && hasColumn(rs, "wishlistID")) {
            user.setWishListList(generateWishLists(USER_ID, rs));
        }

        return user;
    }

    private boolean hasColumn(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    //Helper method for handling list generation
    private List<WishList> generateWishLists(int USER_ID, ResultSet rs) throws SQLException {
        String ConcatenatedWishListName = rs.getString("wishListName");
        String[] wishListNameArray = ConcatenatedWishListName.split(",");

        String concatenatedWishListID = rs.getString("wishlistID");
        String[] wishListIDArray = concatenatedWishListID.split(",");

        if (wishListNameArray.length != wishListIDArray.length) {
            throw new SQLException();
        }

        List<WishList> temp = new ArrayList<>();
        for (int i = 0; i < wishListIDArray.length; i++) {
            temp.add(new WishList(Integer.parseInt(wishListIDArray[i]), wishListNameArray[i], USER_ID));
        }

        return temp;
    }


}




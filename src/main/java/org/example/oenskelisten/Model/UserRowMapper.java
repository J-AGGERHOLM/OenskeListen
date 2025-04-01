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
        final int PERSON_ID = rs.getInt("personID");
        user.setPersonId(PERSON_ID);
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));




        user.setWishListList(generateWishLists(PERSON_ID, rs));


        return user;
    }



    //Helper method for handling list generation
    private List<WishList> generateWishLists(int PERSON_ID, ResultSet rs) throws SQLException {
        String ConcatenatedWishListName = rs.getString("wishListName");
        String[] wishListNameArray = ConcatenatedWishListName.split(",");

        String concatenatedWishListID = rs.getString("wishlistID");
        String[] wishListIDArray = concatenatedWishListID.split(",");

        if (wishListNameArray.length != wishListIDArray.length) {
            throw new SQLException();
        }

        List<WishList> temp = new ArrayList<>();
        for (int i = 0; i < wishListIDArray.length; i++) {
            temp.add(new WishList(Integer.parseInt(wishListIDArray[i]), wishListNameArray[i], PERSON_ID));
        }

        return temp;
    }


}




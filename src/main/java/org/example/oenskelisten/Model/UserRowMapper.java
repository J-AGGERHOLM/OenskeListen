package org.example.oenskelisten.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        //Standard rowMapper stuff:
        var user = new User();
        final int USER_ID = rs.getInt("userID");
        user.setUserID(USER_ID);
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));


        //This is responsible for checking if a column with a certain name exists.
        //The reason it's important, is that if we try to create a new user,
        // and at the same time fetch data from a different table, we will get a nullpointer from setWishListList

        if (hasColumn(rs, "wishListName") && hasColumn(rs, "wishlistID")) {
            user.setWishListList(generateWishLists(USER_ID, rs));
        }
        //So we only set a new wishList object if we find confirm they exist.


        //Otherwise we return a user, with a constructor that doesn't have the wishListList parameter.
        return user;
    }



    //The helper method to check if a column exists.
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

        //gets the wishListName from the SQL query.
        // Because it is concatenated in the SQL query, it has the format of "1,2,3,4,5,6,7"
        String ConcatenatedWishListName = rs.getString("wishListName");
        //Creates an array by splitting the string, based on the "," parameter.
        String[] wishListNameArray = ConcatenatedWishListName.split(",");

        //same
        String concatenatedWishListID = rs.getString("wishlistID");
        String[] wishListIDArray = concatenatedWishListID.split(",");

        //if the lenghts of the two arrays don't match, something is wrong:
        if (wishListNameArray.length != wishListIDArray.length) {
            throw new SQLException();
        }

        //Loops through the two arrays, to create WishList objects, and fill a List of WishLists
        List<WishList> temp = new ArrayList<>();
        for (int i = 0; i < wishListIDArray.length; i++) {
            temp.add(new WishList(Integer.parseInt(wishListIDArray[i]), wishListNameArray[i], USER_ID));
        }

        //returns the willed list of WishLists, to be set in the rowmapper
        return temp;
    }


}




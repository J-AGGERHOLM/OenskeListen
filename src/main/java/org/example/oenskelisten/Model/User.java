package org.example.oenskelisten.Model;

import java.util.List;

public class User {
    private int userID;
    private String name;
    private String email;
    private String password;
    private List<WishList> wishListList;

    public User(int userID, String name, String email, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<WishList> getWishListList() {
        return wishListList;
    }

    public void setWishListList(List<WishList> wishListList) {
        this.wishListList = wishListList;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Bruges til test. Hvis vi skal lave assertEquals på 2 user objekter.
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if(!(other instanceof User)) return false;

        return name.equals(((User) other).name)
                && email.equals(((User) other).email)
                && password.equals(((User) other).password);
    }

    @Override
    public String toString() {
        return "ID: " + userID + " name: " + name + " email: " + email + " Password: " + password;
    }

}

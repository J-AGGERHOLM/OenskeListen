package org.example.oenskelisten.Model;

import java.util.List;

public class User {
    private int personId;
    private String name;
    private String email;
    private String password;
    private List<WishList> wishListList;

    public User(int personId, String name, String email, String password, List<WishList> wishListList){
        this.personId = personId;
        this.name=name;
        this.email=email;
        this.password = password;
    }

    public User(){
    }

    public int getPersonId() {
        return personId;
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

    public void setPersonId(int personId) {
        this.personId = personId;
    }


    @Override
    public String toString(){
        return "ID: " + personId + " name: " + name + " email: " + email + " Password: " + password;
    }

}

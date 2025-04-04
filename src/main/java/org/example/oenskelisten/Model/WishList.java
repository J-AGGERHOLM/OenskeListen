package org.example.oenskelisten.Model;

import java.util.ArrayList;
import java.util.List;

public class WishList {
    private int id;
    private String name;
    private List<Wish> wishes;
    private int userID;


    public WishList() {

    }

    public WishList(int id,String name, int userID) {
        this.id = id;
        this.name = name;
        this.wishes = new ArrayList<>();
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

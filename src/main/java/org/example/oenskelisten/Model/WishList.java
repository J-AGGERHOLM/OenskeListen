package org.example.oenskelisten.Model;

import java.util.List;

public class WishList {
    private int id;
    private List<Wish> wishes;

    public WishList() {

    }

    public WishList(int id, List<Wish> wishes) {
        this.id = id;
        this.wishes = wishes;
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
}

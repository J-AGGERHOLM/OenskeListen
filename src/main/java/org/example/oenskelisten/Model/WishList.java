package org.example.oenskelisten.Model;

import java.util.ArrayList;
import java.util.List;

public class        WishList {
    private int id;



    private String name;
    private List<Wish> wishes;
    private int personID;


    public WishList() {

    }

    public WishList(int id,String name, int personID) {
        this.id = id;
        this.name = name;
        this.wishes = new ArrayList<>();
        this.personID = personID;
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

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }
}

package org.example.oenskelisten.Interface;

import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.Wish;

import java.util.List;

public interface IWishRepository extends IRepository<Wish>{
    boolean edit(Wish newObject);
    Wish getByName(String name);
    List<Wish> getWishListById(int id);

    void reserve(Wish wish, User user);
}

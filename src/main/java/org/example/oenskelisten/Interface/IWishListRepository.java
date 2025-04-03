package org.example.oenskelisten.Interface;

import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Model.WishList;

import java.util.List;

public interface IWishListRepository extends IRepository<Wish> {
    Wish getByName(String name);

    List<WishList> getAllWishLists();

     WishList getWishListByID(int id);
}

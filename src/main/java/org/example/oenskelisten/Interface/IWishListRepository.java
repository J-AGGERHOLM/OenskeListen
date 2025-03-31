package org.example.oenskelisten.Interface;

import org.example.oenskelisten.Model.Wish;

public interface IWishListRepository extends IRepository<Wish> {
    Wish getByName(String name);
}

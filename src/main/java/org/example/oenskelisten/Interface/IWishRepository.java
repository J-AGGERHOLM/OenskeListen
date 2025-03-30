package org.example.oenskelisten.Interface;

public interface IWishRepository<Wish> extends IRepository<Wish> {
    Wish getByName(String name);
    void add(Wish item);
    void deleteById(int id);
}

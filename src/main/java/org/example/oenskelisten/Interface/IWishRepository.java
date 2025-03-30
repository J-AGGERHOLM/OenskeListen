package org.example.oenskelisten.Interface;

import org.example.oenskelisten.Model.Wish;

public interface IWishRepository extends IRepository<Wish> {
    Wish getByName(String name);
    void add(Wish item);
    void deleteById(int id);
}

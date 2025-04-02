package org.example.oenskelisten.Interface;

import org.example.oenskelisten.Model.Wish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IWishListRepository extends IRepository<Wish> {
    Wish getByName(String name);

    List<Wish> getWishListById(int id);

}

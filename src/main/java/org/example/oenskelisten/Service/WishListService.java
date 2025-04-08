package org.example.oenskelisten.Service;

import org.example.oenskelisten.Interface.IWishListRepository;
import org.example.oenskelisten.Interface.IWishRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Model.WishList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private final IWishListRepository wishListRepository;
    private final IWishRepository wishRepository;

    public WishListService(IWishListRepository repository, IWishRepository wishRepository) {
        this.wishListRepository = repository;
        this.wishRepository = wishRepository;
    }

    public WishList getWishListModelByID(int id) {
        WishList list = wishListRepository.getById(id);
        if(list == null) throw new NullPointerException("Id findes ikke: " + id);
        return list;
    }

    // ------------------ WISH ----------------------
    public void addWish(Wish wish) {
        wishRepository.add(wish);
    }

    public List<Wish> getWishListByID(int id) {
        List<Wish> list = wishRepository.getWishListById(id);
        if(list == null) throw new NullPointerException("Id findes ikke: " + id);
        return list;
    }

    public List<Wish> getAllWishListItems() {
        return wishRepository.getAll();
    }

    public Wish getWishById(int id) {
        Wish wish = wishRepository.getById(id);
        if(wish == null) throw new NullPointerException("Id findes ikke: " + id);
        return wish;
    }

    public void updateWish(Wish wish) {
        wishRepository.edit(wish);
    }

    public void deleteWishById(int id) {
        wishRepository.deleteById(id);
    }

    public Wish getByName(String name) {
        return wishRepository.getByName(name);
    }

    public void reserve(Wish wish, User user){
        wishRepository.reserve(wish, user);
    }
}

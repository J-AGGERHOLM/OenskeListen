package org.example.oenskelisten.Service;

import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository repository) {
        this.wishListRepository = repository;
    }

    public List<Wish> getAllWishListItems() {
        return wishListRepository.getAllWishListItems();
    }

    public Wish getWishById(int id) {
        return wishListRepository.getWishById(id);
    }

    public void addWish(Wish wish) {
        wishListRepository.addWish(wish);
    }

    public void updateWish(Wish wish) {
        wishListRepository.updateWish(wish);
    }

    public Wish getWishByName(String name) {
        return wishListRepository.getWishByName(name);
    }

    public void deleteWishById(int id) {
        wishListRepository.deleteWishById(id);
    }








}

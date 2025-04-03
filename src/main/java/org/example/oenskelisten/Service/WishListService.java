package org.example.oenskelisten.Service;

import org.example.oenskelisten.Interface.IWishListRepository;
import org.example.oenskelisten.Model.Wish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private final IWishListRepository wishListRepository;

    public WishListService(IWishListRepository repository) {
        this.wishListRepository = repository;
    }

    public List<Wish> getAllWishListItems() {
        return wishListRepository.getAll();
    }

    public Wish getWishById(int id) {
        return wishListRepository.getById(id);
    }

    public void addWish(Wish wish) {
        wishListRepository.add(wish);
    }

    public void updateWish(Wish wish) {
        wishListRepository.edit(wish);
    }

    public Wish getByName(String name) {
        return wishListRepository.getByName(name);
    }

    public void deleteWishById(int id) {
        wishListRepository.deleteById(id);
    }


    public List<Wish> getWishListByID(int id) {
        return wishListRepository.getWishListById(id);
    }

    public void deleteWishList(int wishListID) {
        wishListRepository.deleteById(wishListID);
    }
}

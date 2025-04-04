package org.example.oenskelisten.Service;

import org.example.oenskelisten.Interface.IWishListRepository;
import org.example.oenskelisten.Interface.IWishRepository;
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

    public WishList getWishListModelByID(int id){
        return wishListRepository.getById(id);
    }

    // ------------------ WISH ----------------------
    public void addWish(Wish wish) {
        wishRepository.add(wish);
    }

    public List<Wish> getWishListByID(int id) {
        return wishRepository.getWishListById(id);
    }

    public List<Wish> getAllWishListItems() {
        return wishRepository.getAll();
    }

    public Wish getWishById(int id) {
        return wishRepository.getById(id);
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
}

package org.example.oenskelisten.Controller;

import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Model.WishList;
import org.example.oenskelisten.Service.UserService;
import org.example.oenskelisten.Service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("wishlist")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService service) {
        this.wishListService = service;
    }

    @GetMapping("/")
    public String getAllWishListItems(Model model) {
        List<Wish> wishListItems = wishListService.getAllWishListItems();
        model.addAttribute("wishListItems", wishListItems);
        return "wishlist";
    }

    @GetMapping("/{id}/wishList")
    public String getWishListByUserID(@PathVariable int id, Model model ){
        List<Wish> wishList = wishListService.getWishListByID(id);
        List<Wish> wishListItems = wishListService.getAllWishListItems();
        model.addAttribute("wishList", wishList);
        model.addAttribute("wishListItems", wishListItems);
        return "wishlist";
    }



    @GetMapping("/{id}/wishListItem")
    public String getWishListItem(@PathVariable int id, Model model) {
        model.addAttribute("wishListItem", wishListService.getWishById(id));
        return "wish-list-item";
    }

    @GetMapping("/add")
    public String addWish(Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        return "add-wish";



    }

    @PostMapping("/save")
    public String addWish(@ModelAttribute("wish") Wish wish) {

        //add functionality for binding a wishlist to the
        //wish later, for now set the wishlistID to be 1:
        wish.setWishlistID(1);
        wishListService.addWish(wish);
        return "redirect:/wishlist";
    }

    @GetMapping("/{id}/edit")
    public String editWish(@PathVariable int id, Model model) {
        Wish wish = wishListService.getWishById(id);
        model.addAttribute("wish", wish);
        return "edit-wish";
    }

    @PostMapping("/update")
    public String editWish(@ModelAttribute("wish") Wish wish) {
        wishListService.updateWish(wish);
        return "redirect:/wishlist";
    }

    @PostMapping("/delete/{name}")
    public String deleteWish(@PathVariable String name) {
        Wish wish = wishListService.getByName(name);
        if(wish == null) {
            throw new IllegalArgumentException("Wish does not exist");
        }
        wishListService.deleteWishById(wish.getId());
        return "redirect:/wishlist";
    }







}

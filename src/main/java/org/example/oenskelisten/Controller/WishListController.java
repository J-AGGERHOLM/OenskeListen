package org.example.oenskelisten.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Model.WishList;
import org.example.oenskelisten.Service.WishListService;
import org.example.oenskelisten.Utils.SessionUtil;
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


    @GetMapping
    public String getAllWishListItems(Model model) {
        List<Wish> wishListItems = wishListService.getAllWishListItems();

        model.addAttribute("wishListItems", wishListItems);
        return "wishlist";
    }

    @GetMapping("/{id}/wishList")
    public String getWishListByUserID(@PathVariable int id, Model model) {
        List<Wish> wishList = wishListService.getWishListByID(id);
        List<Wish> wishListItems = wishListService.getAllWishListItems();

        model.addAttribute("wishList", wishList);
        model.addAttribute("wishListItems", wishListItems);
        return "wishlist";
    }


    @GetMapping("/list/{id}")
    public String getWishList(@PathVariable("id") int id, Model model, HttpSession session) {
        model.addAttribute("pageID", id);

        WishList wishList = wishListService.getWishListModelByID(id);
        //checking to see if we are the owner of the wishlist:
        boolean userIsOwner = SessionUtil.isUserOwner(session, wishList.getUserID());

        //checking to see if we should display the reserve button:
        //logged in but not the owner
        boolean displayReserveInfo = SessionUtil.getLoggedIn(session) && !userIsOwner;

        model.addAttribute("userIsOwner", userIsOwner);
        model.addAttribute("displayReserveInfo", displayReserveInfo);
        model.addAttribute(wishList);
        return "grid-wishlist";
    }

    @GetMapping("/add/{wishlistID}")
    public String addWish(@PathVariable int wishlistID, Model model) {
        Wish wish = new Wish();
        wish.setWishlistID(wishlistID);

        model.addAttribute("wish", wish);
        return "add-wish";
    }

    @PostMapping("/save")
    public String addWish(@ModelAttribute("wish") Wish wish, HttpSession session) {
        String destination = "wishlist/list/" + wish.getWishlistID();

        WishList wishList = wishListService.getWishListModelByID(wish.getWishlistID());
        if (SessionUtil.isUserOwner(session, wishList.getUserID())) {
            wishListService.addWish(wish);
        }

        return "redirect:/" + destination;
    }

    @GetMapping("/{id}/edit")
    public String editWish(@PathVariable int id, Model model) {
        Wish wish = wishListService.getWishById(id);

        model.addAttribute("wish", wish);
        return "edit-wish";
    }

    @PostMapping("/update")
    public String editWish(@ModelAttribute("wish") Wish wish, HttpSession session) {
        WishList wishList = wishListService.getWishListModelByID(wish.getWishlistID());
        if (SessionUtil.isUserOwner(session, wishList.getUserID())) {
            wishListService.updateWish(wish);
        }

        return "redirect:/" + "wishlist/list/" + wish.getWishlistID();
    }

    //the only things sent to deletion correctly are wish id and wishlist id. if you need more
    //you have to change the grid-wishlist html code
    @PostMapping("/delete")
    public String deleteWish(@ModelAttribute("wish") Wish formWish, HttpSession session) {
        Wish wish = wishListService.getWishById(formWish.getId());

        WishList wishList = wishListService.getWishListModelByID(wish.getWishlistID());
        if (SessionUtil.isUserOwner(session, wishList.getUserID())) {
            wishListService.deleteWishById(wish.getId());
        }

        return "redirect:/wishlist/list/" + formWish.getWishlistID();
    }

    @PostMapping("/reserve")
    public String reserveWish(@ModelAttribute("wish") Wish formWish, HttpSession session) {
        Wish wish = wishListService.getWishById(formWish.getId());

        User user = SessionUtil.getCurrentUser(session);
        WishList wishList = wishListService.getWishListModelByID(wish.getWishlistID());
        if (!SessionUtil.isUserOwner(session, wishList.getUserID())) {
            wishListService.reserve(wish, user);
        }

        return "redirect:/wishlist/list/" + formWish.getWishlistID();
    }
}

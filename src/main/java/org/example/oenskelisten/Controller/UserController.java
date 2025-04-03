package org.example.oenskelisten.Controller;

import org.example.oenskelisten.Exception.UnknownErrorException;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.WishList;
import org.example.oenskelisten.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index(Model model) {


        List<WishList> allWishLists = userService.getAllWishlists();
        model.addAttribute(allWishLists);
        //to start off with the user id will simply be one, we will add functionality
        //for multiple users later

        return "index";
    }

    @GetMapping("{id}/user")
    public String userMedID(@PathVariable("id") int id, Model model) {
        if (id <= 0) throw new IllegalArgumentException("Id kan ikke være mindre end 0");

        model.addAttribute("User", userService.getUser(id));

        return "user-page";
    }

    // opdaterer en user
    @PostMapping("update")
    public String updateUser(@ModelAttribute("user") User newUser) {
        if (newUser == null) throw new IllegalArgumentException("User kan ikke være null");

        var result = userService.editUser(newUser);
        if (!result) throw new UnknownErrorException("Noget gik galt");

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/users";
    }

    //Henter alle users
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-all";
    }

    @GetMapping("/user-add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "user-form";
    }

    @PostMapping("/user-create")
    public String addUser(@ModelAttribute("newUser") User newUser, Model redirectAttributes) {
        if (userService.checkEmail(newUser.getEmail()) != null) {
            redirectAttributes.addAttribute("emailTaken", true);
            return "user-form";
        }

        userService.addUser(newUser);

        //makes sure we get the auto incremented id:
        User savedUser = userService.checkEmail(newUser.getEmail());

        return "redirect:/"+savedUser.getUserID()+"/user";


    }

    @GetMapping("/user-page")
    public String userPage() {
        return "user-page";
    }

    // henter layout for edit
    @GetMapping("{id}/edit")
    public String getHandleUser(@PathVariable("id") int id, Model model) {
        if (id == 0) throw new IllegalArgumentException();

        model.addAttribute("user", userService.getUser(id));

        return "user-edit";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        if (id <= 0) throw new IllegalArgumentException("Id kan ikke være mindre end 0");
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/create")
    public String createWishList(Model model) {
        WishList wishlist = new WishList();
        model.addAttribute("wishlist", wishlist);
        return "create-wishlist";
    }

    @PostMapping("/save")
    public String saveWishList(@ModelAttribute("wishlist") WishList wishList) {
        userService.createWishList(wishList);
        return "redirect:user-page";
    }


    @PostMapping("/wishList/{wishListID}/delete")
    public String deleteWishList(@PathVariable int wishListID){
        int userID=userService.getUserIDByWishListID(wishListID);
        userService.deleteWishList(wishListID);
        return "redirect:/" + userID+ "/user";
    }



}

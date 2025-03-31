package org.example.oenskelisten.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.oenskelisten.Exception.UnknownErrorException;
import org.example.oenskelisten.Model.User;
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
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "user-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        return "";

    }

    @GetMapping("{id}/user")
    public String userMedID(@PathVariable("id") int id, Model model) {
        if (id <= 0) throw new IllegalArgumentException("Id kan ikke være mindre end 0");

        model.addAttribute("User", userService.getUser(id));

        return "SpecifikUserSideHer";
    }

    //Henter alle users
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "user-form";
    }

    @PostMapping("/new-user")
    public String addUser(@ModelAttribute ("newUser") User newUser){
        userService.addUser(newUser);
        return "redirect:/userPage";

    }


    // henter layout for edit
    @GetMapping("{id}/edit")
    public String getHandleUser(@PathVariable("id") int id, Model model) {
        if (id == 0) throw new IllegalArgumentException();

        model.addAttribute("user", userService.getUser(id));

        return "editUserSide";
    }

    // opdaterer en user
    @PostMapping("update")
    public String updateUser(@ModelAttribute("user") User newUser) {
        if (newUser == null) throw new IllegalArgumentException("User kan ikke være null");

        var result = userService.editUser(newUser);
        if (!result) throw new UnknownErrorException("Noget gik galt");

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/denSideViSkalRedirectTil";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id){
        if (id <= 0) throw new IllegalArgumentException("Id kan ikke være mindre end 0");
        userService.deleteUser(id);
        return "redirect:/users";
    }


}

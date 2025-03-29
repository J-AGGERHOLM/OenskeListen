package org.example.oenskelisten.Controller;

import org.example.oenskelisten.Exception.UnknownErrorException;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String helloWorld(){
        return "index";
    }

    @GetMapping("{id}/user")
    public String userMedID(@PathVariable("id") int id, Model model){
        if(id <= 0) throw new IllegalArgumentException("Id kan ikke være mindre end 0");

        model.addAttribute("User", userService.getUser(id));

        return "SpecifikUserSideHer";
    }

    //Henter alle users
    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }


    // henter layout for edit
    @GetMapping("{id}/edit")
    public String getHandleAttraction(@PathVariable("id") int id, Model model) {
        if (id == 0) throw new IllegalArgumentException();

        model.addAttribute("user", userService.getUser(id));

        return "editUserSide";
    }

    // opdaterer en user
    @PostMapping("update")
    public String updateAttraction(@ModelAttribute("user") User newUser) {
        if (newUser == null) throw new IllegalArgumentException("User kan ikke være null");

        var result = userService.editUser(newUser);
        if(!result) throw new UnknownErrorException("Noget gik galt");

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/denSideViSkalRedirectTil";
    }



}

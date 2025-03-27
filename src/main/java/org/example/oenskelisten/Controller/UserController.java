package org.example.oenskelisten.Controller;

import org.example.oenskelisten.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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




}

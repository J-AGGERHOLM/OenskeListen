package org.example.oenskelisten.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("")
    public String helloWorld(){
        return "index";
    }

}

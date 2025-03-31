package org.example.oenskelisten;

import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OenskeListenApplication {

    public static void main(String[] args) {
       // SpringApplication.run(OenskeListenApplication.class, args);

        ApplicationContext context = SpringApplication.run(OenskeListenApplication.class);



        var userService = context.getBean(UserService.class);


        userService.getUser(1);
    }

}

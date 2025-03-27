package org.example.oenskelisten.Service;

import org.example.oenskelisten.Repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {

    private  UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

}

package org.example.oenskelisten.Service;

import org.example.oenskelisten.Interface.IUserRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {

    private IUserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get specific user
    public User getUser(int userId) {
        // kontrollere om der er en user.
        var user = userRepository.getById(userId);
        if (user == null) throw new NullPointerException("User not found");

        return userRepository.getById(userId);
    }

    // edit user
    public boolean editUser(User newUser) {
        // Kontrollere om der er en user.
        var oldUser = userRepository.getById(newUser.getPersonId());
        if (oldUser == null) throw new NullPointerException("User not found");

        // indsætter user
        return userRepository.edit(new User(oldUser.getPersonId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPassword()
        ));
    }

}

package org.example.oenskelisten.Service;

import org.example.oenskelisten.Interface.IUserRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {

    private final IUserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get specific user
    public User getUser(int userId) {
        // kontrollere om der er en user.
        var user = userRepository.getById(userId);
        if (user == null) throw new NullPointerException("Id findes ikke: " + userId);

        return user;
    }

    // edit user
    public boolean editUser(User newUser) {
        // Kontrollere om der er en user.
        var oldUser = userRepository.getById(newUser.getPersonId());
        if (oldUser == null) throw new NullPointerException("Id findes ikke: " + newUser.getPersonId());

        // indsætter user
        return userRepository.edit(new User(oldUser.getPersonId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPassword()
        ));
    }
}

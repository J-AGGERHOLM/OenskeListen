package org.example.oenskelisten.Service;

import org.example.oenskelisten.Interface.IUserRepository;
import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Model.WishList;
import org.example.oenskelisten.Repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
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
        var oldUser = userRepository.getById(newUser.getUserID());
        if (oldUser == null) throw new NullPointerException("Id findes ikke: " + newUser.getUserID());

        // indsætter user
        return userRepository.edit(new User(oldUser.getUserID(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getWishListList()));
    }

    //returning all users
    public List<User> getAllUsers() {
        //checks if the list is empty:
        if(userRepository.getAll().isEmpty()){
            throw new NullPointerException("Der er ikke nogen brugere endnu");
        }
        //returns list of users:
        return userRepository.getAll();
    }

    public void addUser(User newUser) {
        userRepository.add(newUser);
    }

    public void deleteUser(int id){
        var user = userRepository.getById(id);

        if (user == null) throw new NullPointerException("Bruger findes ikke" + id);

        userRepository.deleteById(id);
    }

    public User login(String email, String password) {
        // Tjekker om user eksisterer
        var user = userRepository.getByEmail(email);
        if(user == null) return null;

        // tjekker om password passer.
        return user.getPassword().equals(password)
                ? user
                : null;
    }


    public User checkEmail(String email){
        return userRepository.getByEmail(email);


    }

    public void createWishList(WishList wishList) {
        userRepository.createWishList(wishList);
    }


    public void deleteWishList(int wishListID) {
         userRepository.deleteWishList(wishListID);
    }


    public int getUserIDByWishListID(int wishListID){
        return userRepository.getUserIDByWishListID(wishListID);
    }
}

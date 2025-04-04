package org.example.oenskelisten.Interface;

import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.WishList;

// Det er dette interface vi bruger i service for user.
// Hvis vi nu har metoder, som ikke er fælles indsættes de her
public interface IUserRepository extends IRepository<User> {
    User getByEmail(String email);
    boolean edit(User newUser);
    int getUserIDByWishListID(int wishlistID);
}

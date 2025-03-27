package org.example.oenskelisten.Interface;

import org.example.oenskelisten.Model.User;

// Det er dette interface vi bruger i service for user.
// Hvis vi nu har metoder, som ikke er fælles indsættes de her
public interface IUserRepository extends IRepository<User> {
}

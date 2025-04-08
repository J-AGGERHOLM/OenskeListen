package org.example.oenskelisten.Utils;

import jakarta.servlet.http.HttpSession;
import org.example.oenskelisten.Model.User;

// Hvis vi skal lave hjælpe metoder til HttpSession, som skal bruges på de forskellige controllers
// Gør vi det her
public class SessionUtil {

    // Kontrollerer om vores session er en user
    public static User getCurrentUser(HttpSession session) {
        var user = session.getAttribute("user");
        if (user instanceof User) {
            return (User) user;
        }

        return null;
    }

    // Tjekker om man er logget ind
    public static boolean getLoggedIn(HttpSession session) {
        return getCurrentUser(session) != null;
    }

    // Tjekker om sessionId passer til det faktiske id fra databasen
    public static boolean isUserOwner(HttpSession session, int ownerID) {
        var user = getCurrentUser(session);

        return user != null && user.getUserID() == ownerID;
    }
}


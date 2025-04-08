package org.example.oenskelisten.Utils;

import jakarta.servlet.http.HttpSession;
import org.example.oenskelisten.Model.User;

// Hvis vi skal lave hjælpe metoder til HttpSession, som skal bruges på de forskellige controllers
// Gør vi det her
public class SessionUtil {

    // Tjekker om man er logget ind
    public static boolean getLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    public static boolean isUserOwner(HttpSession session, int ownerID){
        var hopefullyAUSer = session.getAttribute("user");
        if(hopefullyAUSer instanceof User loggedInUser){
            if( loggedInUser.getUserID() == ownerID){
                return true;
            }
        }
        return false;
    }
}

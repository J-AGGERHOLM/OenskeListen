package org.example.oenskelisten.Utils;

import jakarta.servlet.http.HttpSession;

// Hvis vi skal lave hjælpe metoder til HttpSession, som skal bruges på de forskellige controllers
// Gør vi det her
public class SessionUtil {

    // Tjekker om man er logget ind
    public static boolean getLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}

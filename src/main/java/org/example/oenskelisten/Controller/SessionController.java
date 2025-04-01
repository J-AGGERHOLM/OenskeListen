package org.example.oenskelisten.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.oenskelisten.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("session")
public class SessionController {
    private final UserService userService;
    // 30 minutter
    private final int MAX_SESSION_LENGTH = 1800;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-login")
    public String getLoginPage() {
        return "user-login";
    }
    @RequestMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model redirectAttributes) {
        // kontrollere om der er en user
        var exist = userService.login(email, password);
        if(exist != null){
            // sætter session og hvornår de logges ud ved inaktivitet
            session.setAttribute("user", exist.getPersonId());
            session.setMaxInactiveInterval(MAX_SESSION_LENGTH);
            return "redirect:/";
        }

        // fortæller, at det indtastede ikke passer
        redirectAttributes.addAttribute("wrongCredentials", true);
        return "user-login";
    }
}

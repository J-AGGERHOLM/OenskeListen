package org.example.oenskelisten.Exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Controller advice griber exception på kryds og tværs af controller
@ControllerAdvice
public class GlobalException {

    // Her fortæller vi hvilken Exception den skal gribe
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNullException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    // Her fortæller vi hvilken Exception den skal gribe
    @ExceptionHandler(NullPointerException.class)
    public String handleNullException(NullPointerException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    @ExceptionHandler(UnknownErrorException.class)
    public String handleUnknownErrorException(UnknownErrorException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }
}

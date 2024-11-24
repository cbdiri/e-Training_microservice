package tn.pi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authController {

    @GetMapping(path = "/auth")
    public String login() {


        return "auth";
    }

    @PostMapping(path = "/auth")
    public String auth(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       Model model) {

        // Vérification des identifiants
        if ("admin".equals(username) && "admin".equals(password)) {
            // Redirection vers une URL externe
            return "redirect:http://localhost:8765/index";
        } else {
            // Ajout d'un message d'erreur au modèle
            model.addAttribute("errorMessage", "Email ou mot de passe incorrect. Veuillez réessayer.");
        }

        // Retour à la vue d'authentification en cas d'erreur
        return "auth";
    }
}

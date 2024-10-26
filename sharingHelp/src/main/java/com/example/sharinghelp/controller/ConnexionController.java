package com.example.sharinghelp.controller;

import com.example.sharinghelp.entities.Compte;
import com.example.sharinghelp.service.CompteService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.util.List;

@Controller
public class ConnexionController {
    @Autowired
    CompteService service;


    @GetMapping("/connexionForm")
    public String pageConnexion (Model model){

        Compte compte = new Compte();
        model.addAttribute("compte",compte);


        return  "connexion";
    }

    @PostMapping("/connexion")
    public String seConnecter(Compte compte,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes){

        if (compte != null){
            Compte newCompte = service.seConnecter(compte.getEmail(),compte.getPassword());
            System.out.println(newCompte);
            if (newCompte != null) {
                HttpSession session = request.getSession();
                session.setAttribute("compte", newCompte);

                // Définir l'attribut "message" dans la session avec un message qui n'affecte pas les autres attributs session
                session.setAttribute("message", "Bonjour, " + newCompte.getUsername() + " s'est connecté !");

                String seSouvenirDeMoi = request.getParameter("sauvegarde");
                if ("yes".equals(seSouvenirDeMoi)) {
                    Cookie emailCookie = new Cookie("email", newCompte.getEmail());
                    Cookie motDePasseCookie = new Cookie("password", compte.getPassword());

                    emailCookie.setMaxAge(Duration.ofDays(60*60).getNano());
                    motDePasseCookie.setMaxAge(Duration.ofDays(60*60).getNano());

                    response.addCookie(emailCookie);
                    response.addCookie(motDePasseCookie);
                }
                return "redirect:/";
            }
        }
        return "redirect:/connexionForm";
    }


    @GetMapping("/deconnexion")
    public String seDeconnecter(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

}



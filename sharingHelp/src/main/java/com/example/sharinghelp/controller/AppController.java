package com.example.sharinghelp.controller;

import com.example.sharinghelp.entities.Compte;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String pageIndexAdmin (HttpSession session, Model model){
        Compte compte = (Compte) session.getAttribute("compte");
        if (compte != null) {
            model.addAttribute("compte", compte);
        }
        return "index";
    }
    @GetMapping("/faqA")
    public String pageFaqAdmin (){
        return "faqAdmin";
    }
}
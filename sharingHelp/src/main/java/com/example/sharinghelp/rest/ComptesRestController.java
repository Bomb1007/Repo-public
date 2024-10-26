package com.example.sharinghelp.rest;

import com.example.sharinghelp.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComptesRestController {
    @Autowired
    CompteService service;

    @PostMapping("/comptes/check_email")
    public String verifierDoublonEmail(@Param("email") String email, @Param("id") Integer id) {
        return service.isEmailUnique(email,id) ? "OK" : "Doublon";
    }
}

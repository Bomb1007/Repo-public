package com.example.sharinghelp.service;

import com.example.sharinghelp.entities.Compte;
import com.example.sharinghelp.repos.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CompteService {

    @Autowired
    CompteRepository repos;

    public List<Compte> afficherComptes(){
        return ( List<Compte> ) repos.findAll();
    }

    public List<Compte> rechercherParKeyword(String keyword) {
        if (keyword != null) {
            return repos.findAll(keyword);
        }
        return  null;
    }

    public Compte seConnecter(String email, String password){
        return  repos.findByEmailAndPassword(email, password);
    }
    public Compte chercher(int id){
        return repos.findById(id).get();
    }

    public void supprimer(int id){
        repos.deleteById(id);
    }

    public Compte enregistrer(Compte compte){
        return repos.save(compte);
    }

    public List<Compte> afficherMembresProjet(Long projetId) {
        return repos.findByProjetsId(projetId);
    }

    public boolean isEmailUnique(String email,Integer id) {
        Compte compteByEmail = repos.findByEmail(email);
        if (compteByEmail == null) return true;
        boolean isCreatingNew = false;
        if (id == null){
            isCreatingNew=true;
        }
        if(isCreatingNew){
            if (compteByEmail != null) return false;
        }else{
            if (compteByEmail.getId() != Long.parseLong(String.valueOf(id))) {
                return false;
            }
        }
        //dans tous les cas on peut creer, editer
        return true;

    }
}

package com.example.sharinghelp.service;

import com.example.sharinghelp.entities.Compte;
import com.example.sharinghelp.entities.Projet;
import com.example.sharinghelp.repos.CompteRepository;
import com.example.sharinghelp.repos.ProjetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetService {

    @Autowired
    ProjetRepository repos;
    @Autowired
    CompteRepository compteRepository;

    public List<Projet> afficherProjets() {
        return (List<Projet>) repos.findAll();
    }

    public void supprimer(int id) {
        repos.deleteById(id);
    }

    public Projet enregistrer(Projet projet) {
        return repos.save(projet);
    }

    public Projet chercher(int id) {
        return repos.findById(id).get();
    }

    public Optional<Projet> afficherDetailProjet(int idProjet) {
        Optional<Projet> projetRecherche = repos.findById(idProjet);
        return projetRecherche;
    }

    public Projet findProjetById(int idProjet) {
        Projet projetRecherche;
        projetRecherche = repos.findById(idProjet).orElse(null);
        return projetRecherche;
    }

    @Transactional
    public void joinCommunity(Integer projetId, Compte compte) throws EntityNotFoundException, IllegalStateException {
        Projet projet = repos.findById(projetId)
                .orElseThrow(() -> new EntityNotFoundException("Communauté not found with ID: " + projetId));

        if (projet.getMembres().contains(compte)) {
            throw new IllegalStateException("Compte is already a member of the communaute.");
        }

        projet.getMembres().add(compte);
        repos.save(projet);
    }

        public List<Projet> rechercherParKeyword (String keyword){
            if (keyword != null) {
                return repos.findAll(keyword);
            }
            return null;
        }
    }






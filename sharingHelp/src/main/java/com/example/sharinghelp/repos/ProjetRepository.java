package com.example.sharinghelp.repos;

import com.example.sharinghelp.entities.Compte;
import com.example.sharinghelp.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Integer> {

    @Query("SELECT p FROM Projet p WHERE p.titre LIKE %?1% OR p.description LIKE %?1%")
    public List<Projet> findAll(String keyword);

}

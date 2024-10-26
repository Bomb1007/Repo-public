package com.example.sharinghelp.repos;

import com.example.sharinghelp.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Integer> {
    public Compte findByEmailAndPassword(String email,String password);

    public Compte findByEmail(String email);

    List<Compte> findByProjetsId(Long projetsId);

    @Query("SELECT c FROM Compte c WHERE c.nom  LIKE %?1% OR c.username LIKE %?1%")
    public List<Compte> findAll(String keyword);
}

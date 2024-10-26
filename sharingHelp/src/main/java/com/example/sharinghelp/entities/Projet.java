package com.example.sharinghelp.entities;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projet")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dateDebut")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @Column(name = "dateFin")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

    @Column(name = "description", columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(name = "titre")
    private String titre;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "idCreateur", referencedColumnName = "id", nullable = false)
    private Compte createur;

    @Column(name = "objectifs", columnDefinition = "TEXT")
    private String objectifs;

    @Column(name = "ressources_necessaires", columnDefinition = "TEXT")
    private String ressourcesNecessaires;

    @Column(name = "progression_actuelle", columnDefinition = "TEXT")
    private String progressionActuelle;

    @Column(name = "impact_attendu", columnDefinition = "TEXT")
    private String impactAttendu;

    @ManyToMany
    @JoinTable(
            name = "projet_membre",
            joinColumns = @JoinColumn(name = "projet_id"),
            inverseJoinColumns = @JoinColumn(name = "membre_id")
    )
    private Set<Compte> membres;

    public Set<Compte> getMembres() {
        return membres;
    }

    public void setMembres(Set<Compte> membres) {
        this.membres = membres;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Compte getCreateur() {
        return createur;
    }

    public void setCreateur(Compte createur) {
        this.createur = createur;
    }

    public String getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(String objectifs) {
        this.objectifs = objectifs;
    }

    public String getRessourcesNecessaires() {
        return ressourcesNecessaires;
    }

    public void setRessourcesNecessaires(String ressourcesNecessaires) {
        this.ressourcesNecessaires = ressourcesNecessaires;
    }

    public String getProgressionActuelle() {
        return progressionActuelle;
    }

    public void setProgressionActuelle(String progressionActuelle) {
        this.progressionActuelle = progressionActuelle;
    }

    public String getImpactAttendu() {
        return impactAttendu;
    }

    public void setImpactAttendu(String impactAttendu) {
        this.impactAttendu = impactAttendu;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", description='" + description + '\'' +
                ", titre='" + titre + '\'' +
                ", photo='" + photo + '\'' +
                ", createur=" + createur +
                ", objectifs='" + objectifs + '\'' +
                ", ressourcesNecessaires='" + ressourcesNecessaires + '\'' +
                ", progressionActuelle='" + progressionActuelle + '\'' +
                ", impactAttendu='" + impactAttendu + '\'' +
                '}';
    }
}
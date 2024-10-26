package com.example.sharinghelp.controller.Admin;

import com.example.sharinghelp.entities.Compte;
import com.example.sharinghelp.entities.Projet;
import com.example.sharinghelp.service.CompteService;
import com.example.sharinghelp.service.ProjetService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class GestionProjets {
    @Autowired
    ProjetService service;
    @Autowired
    CompteService serviceComptes;

    @GetMapping("/adminProjets")
    public String afficherComptesAdmin(Model model){
        List<Projet> listProjet = service.afficherProjets();
        model.addAttribute("listProjet",listProjet);
        return "adminProjets";
    }
    @PostMapping("/adminProjetsDelete")
    public String deleteProjetsAdmin(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        service.supprimer(id);
        return "redirect:/adminProjets";
    }
    @GetMapping("/creerProjetForm")
    public String pageFormulaireProjet (Model model){
        List<Compte> listComptes = serviceComptes.afficherComptes();
        model.addAttribute("listComptes",listComptes);
        Projet projet = new Projet();
        model.addAttribute("projet",projet);
        return  "creerProjetAdmin";
    }
    @PostMapping("/adminProjetsUpdate")
    public String updateProhetssAdmin(Model model,HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));

        List<Compte> listComptes = serviceComptes.afficherComptes();
        model.addAttribute("listComptes",listComptes);
        Projet projet=service.chercher(id);
        model.addAttribute("projet",projet);
        return  "creerProjetAdmin";
    }


    @PostMapping("/adminProjetCreate")
    public String ajouterProjetAdmin(Projet projet, RedirectAttributes redirectAttributes,HttpServletRequest request,
                                     @RequestParam("fileImage") MultipartFile multipartFile){

        String chemin =  multipartFile.getOriginalFilename();
        String typeContenu = multipartFile.getContentType();
        String fileName =   StringUtils.cleanPath(chemin);
        String dateDebutStr = request.getParameter("dateDebut");
        String dateFinStr = request.getParameter("dateFin");
        int idCreateur = Integer.parseInt(request.getParameter("idCreateur"));

        Compte compte = serviceComptes.chercher(idCreateur);
        LocalDate dateDebutLocalDate = LocalDate.parse(dateDebutStr);
        LocalDate dateFinLocalDate = LocalDate.parse(dateFinStr);

        Date dateDebut = Date.from(dateDebutLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFin = Date.from(dateFinLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        redirectAttributes.addFlashAttribute("message","Le projet a été ajouté avec succès") ;
        if (projet!=null){
            projet.setPhoto(fileName);
            projet.setDateDebut(dateDebut);
            projet.setDateFin(dateFin);
            projet.setCreateur(compte);
            service.enregistrer(projet);
            return "redirect:/adminProjets";
        }
        return "redirect:/creerProjetForm";
    }
    @GetMapping("/rechercher/projets")
    public String rechercherUtilisateur(
            Model model, @Param("keyword") String keyword)
    {
        List<Projet> listProjet = service.rechercherParKeyword(keyword);

        model.addAttribute("listProjet",listProjet);
        model.addAttribute("keyword", keyword);
        return "adminProjets";
    }
}

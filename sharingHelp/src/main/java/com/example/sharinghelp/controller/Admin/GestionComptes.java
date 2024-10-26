package com.example.sharinghelp.controller.Admin;

import com.example.sharinghelp.entities.Compte;
import com.example.sharinghelp.service.CompteService;
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

import java.util.List;

@Controller
public class GestionComptes {
    @Autowired
    CompteService service;

    @GetMapping("/adminComptes")
    public String afficherComptesAdmin(Model model){
        List<Compte> listcomptes = service.afficherComptes();
        model.addAttribute("listcomptes",listcomptes);
        return "adminComptes";
    }
    @PostMapping("/adminComptesDelete")
    public String deleteComptesAdmin(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        service.supprimer(id);
        return "redirect:/adminComptes";
    }
    @PostMapping("/adminComptesUpdate")
    public String updateComptesAdmin(Model model,HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Compte compte=service.chercher(id);
        model.addAttribute("compte",compte);
        return  "creerCompteAdmin";
    }
    @GetMapping("/creerCompteForm")
    public String pageFormulaireCompte (Model model){
        Compte compte = new Compte();
        model.addAttribute("compte",compte);
        return  "creerCompteAdmin";
    }
    @PostMapping("/adminComptesCreate")
    public String ajouterCompteAdmin(Compte compte, RedirectAttributes redirectAttributes,
                                     @RequestParam("fileImage") MultipartFile multipartFile){

        String chemin =  multipartFile.getOriginalFilename();
        String typeContenu = multipartFile.getContentType();
        String fileName =   "Img/pfp_utilisateurs/"+StringUtils.cleanPath(chemin);

        if (compte!=null){
            compte.setPhoto(fileName);
            service.enregistrer(compte);
            redirectAttributes.addFlashAttribute("message","Le compte a été ajouté avec succès") ;
            return "redirect:/adminComptes";
        }
        return "redirect:/creerCompteForm";
    }

    @GetMapping("/rechercher/comptes")
    public String rechercherUtilisateur(
            Model model, @Param("keyword") String keyword)
    {
        List<Compte> listcomptes = service.rechercherParKeyword(keyword);

        model.addAttribute("listcomptes",listcomptes);
        model.addAttribute("keyword", keyword);
        return "adminComptes";
    }

    @PostMapping("/adminProjetsMembres")
    public String afficherMembresProjet(Model model,HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));
        List<Compte> listcomptes = service.afficherMembresProjet(id);
        model.addAttribute("listcomptes",listcomptes);
        return "adminComptes";
    }
}

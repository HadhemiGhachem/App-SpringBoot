package com.gestion.store.controller;

import com.gestion.store.entities.Auteur;
import com.gestion.store.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auteurs")
public class AuteurController {
    @Autowired
    private AuteurService auteurService;

    @GetMapping
    public String listAuteurs(Model model, @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        Page<Auteur> auteurs = auteurService.getAuteurs(page, size);
        model.addAttribute("auteurs", auteurs);
        model.addAttribute("currentPage", page);
        return "listauteur";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("auteur", new Auteur());
        return "addauteur";
    }

    @PostMapping("/add")
    public String addAuteur(@ModelAttribute Auteur auteur) {
        auteurService.saveAuteur(auteur);
        return "redirect:/auteurs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Auteur auteur = auteurService.getAuteurById(id);
        model.addAttribute("auteur", auteur);
        return "addauteur";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuteur(@PathVariable Long id) {
        auteurService.deleteAuteur(id);
        return "redirect:/auteurs";
    }
}
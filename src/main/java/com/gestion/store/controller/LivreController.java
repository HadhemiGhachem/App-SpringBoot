
        package com.gestion.store.controller;

import com.gestion.store.entities.Livre;
import com.gestion.store.repository.AuteurRepository;
import com.gestion.store.repository.CategorieRepository;
import com.gestion.store.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/livres")
public class LivreController {
    @Autowired
    private LivreService livreService;
    @Autowired
    private AuteurRepository auteurRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public String listLivres(Model model, @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "2") int size) {
        Page<Livre> livres = livreService.getLivres(page, size);
        model.addAttribute("livres", livres);
        model.addAttribute("currentPage", page);
        return "listlivre";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("livre", new Livre());
        model.addAttribute("auteurs", auteurRepository.findAll());
        model.addAttribute("categories", categorieRepository.findAll());
        return "addlivre";
    }

    @PostMapping("/add")
    public String addLivre(@ModelAttribute Livre livre, @RequestParam("image") MultipartFile image) throws IOException {
        livreService.saveLivre(livre, image);
        return "redirect:/livres";
    }
}

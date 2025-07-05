
        package com.gestion.store.controller;

import com.gestion.store.entities.Categorie;
import com.gestion.store.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public String listCategories(Model model, @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Page<Categorie> categories = categorieService.getCategories(page, size);
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        return "listcategorie";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "addcategorie";
    }

    @PostMapping("/add")
    public String addCategorie(@ModelAttribute Categorie categorie) {
        categorieService.saveCategorie(categorie);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Categorie categorie = categorieService.getCategorieById(id);
        model.addAttribute("categorie", categorie);
        return "addcategorie";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return "redirect:/categories";
    }
}

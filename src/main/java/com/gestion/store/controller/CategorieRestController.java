
        package com.gestion.store.controller;

import com.gestion.store.entities.Categorie;
import com.gestion.store.service.CategorieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categorie API", description = "API for managing categories")
public class CategorieRestController {
    @Autowired
    private CategorieService categorieService;

    @GetMapping
    @Operation(summary = "List all categories with pagination")
    public Page<Categorie> getCategories(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return categorieService.getCategories(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by ID")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Categorie categorie = categorieService.getCategorieById(id);
        return ResponseEntity.ok(categorie);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        categorieService.saveCategorie(categorie);
        return ResponseEntity.ok(categorie);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        categorie.setId(id);
        categorieService.saveCategorie(categorie);
        return ResponseEntity.ok(categorie);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}

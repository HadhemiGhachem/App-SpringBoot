
        package com.gestion.store.controller;

import com.gestion.store.entities.Auteur;
import com.gestion.store.service.AuteurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auteurs")
@Tag(name = "Auteur API", description = "API for managing authors")
public class AuteurRestController {
    @Autowired
    private AuteurService auteurService;

    @GetMapping
    @Operation(summary = "List all authors with pagination")
    public Page<Auteur> getAuteurs(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        return auteurService.getAuteurs(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an author by ID")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Long id) {
        Auteur auteur = auteurService.getAuteurById(id);
        return ResponseEntity.ok(auteur);
    }

    @PostMapping
    @Operation(summary = "Create a new author")
    public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur auteur) {
        auteurService.saveAuteur(auteur);
        return ResponseEntity.ok(auteur);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing author")
    public ResponseEntity<Auteur> updateAuteur(@PathVariable Long id, @RequestBody Auteur auteur) {
        Auteur existingAuteur = auteurService.getAuteurById(id);
        existingAuteur.setNom(auteur.getNom());
        existingAuteur.setPrenom(auteur.getPrenom());
        auteurService.saveAuteur(existingAuteur);
        return ResponseEntity.ok(existingAuteur);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an author")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Long id) {
        auteurService.deleteAuteur(id);
        return ResponseEntity.noContent().build();
    }
}

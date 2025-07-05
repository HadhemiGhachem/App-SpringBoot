package com.gestion.store.controller;

import com.gestion.store.entities.Livre;
import com.gestion.store.service.LivreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/livres")
@Tag(name = "Livre API", description = "API for managing books")
public class LivreRestController {
    @Autowired
    private LivreService livreService;

    @GetMapping
    @Operation(summary = "Get all books with pagination")
    public Page<Livre> getLivres(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        return livreService.getLivres(page, size);
    }

    @PostMapping
    @Operation(summary = "Create a new book with image")
    public ResponseEntity<Livre> createLivre(@RequestPart("livre") Livre livre,
                                             @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        Livre savedLivre = livreService.saveLivre(livre, image);
        return ResponseEntity.ok(savedLivre);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID")
    public ResponseEntity<Livre> getLivre(@PathVariable Long id) {
        Livre livre = livreService.getLivre(id);
        return livre != null ? ResponseEntity.ok(livre) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by ID")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return ResponseEntity.noContent().build();
    }
}
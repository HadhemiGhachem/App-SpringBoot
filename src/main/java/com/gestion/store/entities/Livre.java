package com.gestion.store.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String isbn;
    private String imagePath;

    @ManyToOne
    private Auteur auteur;

    @ManyToOne
    private Categorie categorie;

    public void setImagePath(String s) {
        this.imagePath = imagePath;

    }
}
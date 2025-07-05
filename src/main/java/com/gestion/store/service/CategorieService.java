
        package com.gestion.store.service;

import com.gestion.store.entities.Categorie;
import com.gestion.store.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public Page<Categorie> getCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categorieRepository.findAll(pageable);
    }

    public void saveCategorie(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie not found"));
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}

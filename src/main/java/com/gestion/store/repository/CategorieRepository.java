package com.gestion.store.repository;

import com.gestion.store.entities.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Page<Categorie> findAll(Pageable pageable);
}
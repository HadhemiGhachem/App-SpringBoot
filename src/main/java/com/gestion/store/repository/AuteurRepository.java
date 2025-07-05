
        package com.gestion.store.repository;

import com.gestion.store.entities.Auteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    Page<Auteur> findAll(Pageable pageable);
}

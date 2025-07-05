
        package com.gestion.store.service;

import com.gestion.store.entities.Auteur;
import com.gestion.store.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AuteurService {
    @Autowired
    private AuteurRepository auteurRepository;

    public Page<Auteur> getAuteurs(int page, int size) {
        return auteurRepository.findAll(PageRequest.of(page, size));
    }

    public Auteur getAuteurById(Long id) {
        return auteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auteur non trouvé avec l'ID: " + id));
    }

    public void saveAuteur(Auteur auteur) {
        auteurRepository.save(auteur);
    }

    public void deleteAuteur(Long id) {
        if (!auteurRepository.existsById(id)) {
            throw new RuntimeException("Auteur non trouvé avec l'ID: " + id);
        }
        auteurRepository.deleteById(id);
    }
}

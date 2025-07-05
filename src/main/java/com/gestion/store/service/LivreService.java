package com.gestion.store.service;

import com.gestion.store.entities.Livre;
import com.gestion.store.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public Page<Livre> getLivres(int page, int size) {
        return livreRepository.findAll(PageRequest.of(page, size));
    }

    public Livre saveLivre(Livre livre, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/uploads/" + fileName);
            Files.write(path, image.getBytes());
            livre.setImagePath("/uploads/" + fileName);
        }
        return livreRepository.save(livre);
    }

    public Livre getLivre(Long id) {
        return livreRepository.findById(id).orElse(null);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }
}
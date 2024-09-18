package com.myproject.organization.services;

import com.myproject.organization.entites.Image;
import com.myproject.organization.repository.ImageRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public Image findById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        return image.orElseThrow(() -> new RuntimeException("Image not found!"));
    }

    public List<Image> findAll() {
        return imageRepository.findAll();
    }
}

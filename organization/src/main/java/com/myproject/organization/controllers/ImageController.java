package com.myproject.organization.controllers;

import com.myproject.organization.entites.Image;
import com.myproject.organization.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestBody Image image) {
        Image uploadedImage = imageService.save(image);
        return new ResponseEntity<>(uploadedImage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        Image image = imageService.findById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.findAll();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
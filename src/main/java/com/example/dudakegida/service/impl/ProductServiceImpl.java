package com.example.dudakegida.service.impl;

import com.example.dudakegida.model.PetFood;
import com.example.dudakegida.repository.AnimalRepository;
import com.example.dudakegida.repository.ProductRepository;
import com.example.dudakegida.repository.UserRepository;
import com.example.dudakegida.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(AnimalRepository animalRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<PetFood> allFood() {
        return productRepository.findAll();
    }

    @Override
    public PetFood findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public String saveImg(MultipartFile imageFile) throws IOException {
        String folder = "C:\\Users\\user\\DudakEgida\\src\\main\\resources\\static\\images\\uploadFoodImg\\";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path, bytes);

        return "/images/uploadFoodImg/" + imageFile.getOriginalFilename();
    }

    @Override
    public void save(PetFood petFood) {
        productRepository.save(petFood);
    }

    @Override
    public List<PetFood> findAll() {
        return productRepository.findAll();
    }
}

package com.example.dudakegida.service;

import com.example.dudakegida.model.PetFood;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<PetFood> allFood();
    PetFood findById(Long id);
    String saveImg(MultipartFile imageFile) throws IOException;
    void save(PetFood petFood);
    List<PetFood> findAll();
}

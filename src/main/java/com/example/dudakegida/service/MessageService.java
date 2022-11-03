package com.example.dudakegida.service;

import com.example.dudakegida.model.Message;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MessageService {
    List<Message> findAll();

    void save(Message message);

    String saveImg(MultipartFile imageFile) throws IOException;

    String cutPost(String post);

    List<Message> getListOfPostByType(String type);

    Message findById(Long id);

    List<Message> reverseList(List<Message> list);
}

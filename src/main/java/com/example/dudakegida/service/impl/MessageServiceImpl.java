package com.example.dudakegida.service.impl;

import com.example.dudakegida.model.Message;
import com.example.dudakegida.repository.MessageRepository;
import com.example.dudakegida.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public String saveImg(MultipartFile imageFile) throws IOException {
        String folder = "C:\\Users\\user\\DudakEgida\\src\\main\\resources\\static\\images\\news\\";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path, bytes);
        return "/images/news/" + imageFile.getOriginalFilename();
    }

    @Override
    public String cutPost(String post) {
        String cut;
        if(post.length()>120){
            cut = post.substring(0,120) + "...";
        } else{
            cut = post;
        }
        return cut;
    }

    @Override
    public List<Message> getListOfPostByType(String type) {
        return null;
    }

    @Override
    public Message findById(Long id) {
        return messageRepository.findById(id).orElse(new Message());
    }

    @Override
    public List<Message> reverseList(List<Message> list) {
        Collections.reverse(list);
        return list;
    }
}

package com.example.dudakegida.repository;

import com.example.dudakegida.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

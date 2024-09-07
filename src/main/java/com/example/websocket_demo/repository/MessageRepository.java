package com.example.websocket_demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.websocket_demo.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	 // Lấy danh sách tin nhắn theo recipient (ID người nhận hoặc nhóm)
    List<Message> findByRecipient(String recipient);

    // Lấy danh sách tin nhắn theo loại và recipient
    List<Message> findByMessageTypeAndRecipient(String messageType, String recipient);
}

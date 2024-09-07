package com.example.websocket_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.websocket_demo.model.Message;
import com.example.websocket_demo.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
    private MessageRepository messageRepository;

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getMessagesByRecipient(String recipient) {
        return messageRepository.findByRecipient(recipient);
    }

    public List<Message> getMessagesByTypeAndRecipient(String messageType, String recipient) {
        return messageRepository.findByMessageTypeAndRecipient(messageType, recipient);
    }
}

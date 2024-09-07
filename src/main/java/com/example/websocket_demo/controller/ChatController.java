package com.example.websocket_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.example.websocket_demo.model.Message;
import com.example.websocket_demo.service.MessageService;

@Controller
public class ChatController {
	
//	@Autowired
//    private MessageService messageService;
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public Message sendMessage(@Payload Message chatMessage) {
//		messageService.saveMessage(chatMessage);
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public Message addUser(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		// Add username in web socket session
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}
	
	@MessageMapping("/chat.sendTo")
	@SendTo("/topic/messages")
	public Message send(Message message) {
		// Xử lý message và gửi đến các subscriber
//		messageService.saveMessage(message);
		return message;
	}
	
	@MessageMapping("/group/{groupId}")
    @SendTo("/topic/group/{groupId}")
    public Message sendGroupMessage(@DestinationVariable String groupId, Message message,
                                        SimpMessageHeaderAccessor headerAccessor) {
        // Có thể xử lý thêm logic khác tại đây, như lưu tin nhắn vào cơ sở dữ liệu
//		messageService.saveMessage(message);
        return message;
    }
}

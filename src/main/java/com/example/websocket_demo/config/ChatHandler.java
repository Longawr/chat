package com.example.websocket_demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.websocket_demo.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatHandler extends TextWebSocketHandler {
	// Lưu trữ các phiên làm việc của người dùng
    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username = (String) session.getAttributes().get("username");
        sessions.put(username, session); // Lưu lại session theo tên người dùng
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Giả sử message có dạng JSON: {"to": "userB", "message": "Hello"}
        String payload = message.getPayload();
        // Parse JSON (bạn có thể dùng thư viện như Jackson hoặc Gson)
        // Ví dụ sử dụng Jackson:
        ObjectMapper mapper = new ObjectMapper();
        Message chatMessage = mapper.readValue(payload, Message.class);

        // Lấy session của người nhận
        WebSocketSession recipientSession = sessions.get(chatMessage.getRecipient());

        if (recipientSession != null && recipientSession.isOpen()) {
            // Gửi thông điệp cho người nhận
            recipientSession.sendMessage(new TextMessage(chatMessage.getContent()));
        }
    }
}

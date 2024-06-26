package com.example.websocket;

import org.springframework.lang.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
        String payload = message.getPayload();
        String response = processUserInput(payload);

        for (int i = 0; i < response.length(); i++) {
            session.sendMessage(new TextMessage(response.substring(0, i + 1)));
            Thread.sleep(30);  // Adjust the sleep duration as needed
        }
    }

    private String processUserInput(String message) {
        message = message.toLowerCase();
        if (message.contains("hello")) {
            return "Hello! How can I assist you today?";
        } else if (message.contains("how are you") || message.contains("are you okay")) {
            return "I'm just a program, but thanks for asking!";
        } else if (message.contains("bye") || message.contains("goodbye")) {
            return "Goodbye! Have a great day.";
        } else {
            return "Sorry, I didn't understand that. Can you please clarify?";
        }
    }
}

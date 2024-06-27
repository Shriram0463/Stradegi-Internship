package com.example.websocket.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        String response = processUserInput(payload);

        for (int i = 0; i < response.length(); i++) {
            String partialResponse = response.substring(0, i + 1);
            session.sendMessage(new TextMessage(partialResponse));
            Thread.sleep(50);  // Adjust the sleep duration as needed
        }
    }

    private String processUserInput(String message) {
        message = message.toLowerCase();
        if (message.contains("hello")) {
            return "Hello! How can I assist you today?";
        } else if (message.contains("bye")) {
            return "Goodbye! Have a great day.";
        } else {
            return "Sorry, I didn't understand that. Can you please clarify?";
        }
    }
}

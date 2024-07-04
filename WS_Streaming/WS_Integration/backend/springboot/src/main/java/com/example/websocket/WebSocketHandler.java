package com.example.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.io.IOException;
import java.util.concurrent.CompletionStage; 

public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    private final String fastApiUrl = "ws://localhost:8000/ws"; // Replace with your FastAPI WebSocket URL

    private WebSocket fastApiWebSocket;

    public WebSocketHandler() {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> wsFuture = client.newWebSocketBuilder()
                .buildAsync(URI.create(fastApiUrl), new FastApiWebSocketListener());
        wsFuture.thenAccept(webSocket -> fastApiWebSocket = webSocket);
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
        String payload = message.getPayload();

        // Validate the payload
        if (payload == null || payload.trim().isEmpty()) {
            logger.error("Received empty or null payload");
            session.sendMessage(new TextMessage("Error: Received empty or null payload"));
            return;
        }

        if (fastApiWebSocket != null) {
            fastApiWebSocket.sendText(payload, true);
            // Store the session to forward the response back to the client
            FastApiWebSocketListener.setSession(session);
        } else {
            logger.error("WebSocket connection to FastAPI is not established");
            session.sendMessage(new TextMessage("Error: WebSocket connection to FastAPI is not established"));
        }
    }

    private static class FastApiWebSocketListener implements WebSocket.Listener {

        private static WebSocketSession session;

        public static void setSession(WebSocketSession webSocketSession) {
            session = webSocketSession;
        }

        @Override
        public void onOpen(WebSocket webSocket) {
            webSocket.request(1);
            logger.info("Connected to FastAPI WebSocket");
        }

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            try {
                if (session != null && session.isOpen()) {
                    session.sendMessage(new TextMessage(data.toString()));
                }
            } catch (IOException e) {
                logger.error("Error sending message to client: {}", e.getMessage());
            }
            webSocket.request(1);
            return null;
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            logger.error("WebSocket error: {}", error.getMessage());
        }
    }
}
